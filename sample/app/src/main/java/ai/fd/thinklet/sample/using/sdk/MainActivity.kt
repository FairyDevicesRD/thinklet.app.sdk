package ai.fd.thinklet.sample.using.sdk

import ai.fd.thinklet.sample.using.sdk.databinding.ActivityMainBinding
import ai.fd.thinklet.sdk.audio.MicGainControl
import ai.fd.thinklet.sdk.audio.MultiChannelAudioRecord
import ai.fd.thinklet.sdk.audio.RawAudioRecordWrapper
import ai.fd.thinklet.sdk.audio.VolumeControl
import ai.fd.thinklet.sdk.gesture.ExperimentalFeature
import ai.fd.thinklet.sdk.gesture.GestureSensorEventCallback
import ai.fd.thinklet.sdk.gesture.GestureSensorManager
import ai.fd.thinklet.sdk.gesture.WearSensorManager
import ai.fd.thinklet.sdk.led.LedClient
import ai.fd.thinklet.sdk.maintenance.adb.AdbClient
import ai.fd.thinklet.sdk.maintenance.camera.Angle
import ai.fd.thinklet.sdk.maintenance.language.Language
import ai.fd.thinklet.sdk.maintenance.launcher.Extension
import ai.fd.thinklet.sdk.maintenance.power.PowerController
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private companion object {
        private const val TAG = "${BuildConfig.APPLICATION_ID}_SDK_Sample"
        private const val REQUEST_CODE = 1000
    }

    // audio
    private val randomFileName: String
        get() = "6ch_48kHz_${
            SimpleDateFormat("yyyy-MM-dd-hh-mm-ss", Locale.getDefault()).format(
                Date()
            )
        }.raw"
    private val rawFileOutputStream: FileOutputStream
        get() = FileOutputStream(File(this.getExternalFilesDir(null), randomFileName))
    private val rawRecorder = RawAudioRecordWrapper(
        channel = MultiChannelAudioRecord.Channel.CHANNEL_SIX,
        sampleRate = MultiChannelAudioRecord.SampleRate.SAMPLING_RATE_48000,
        outputChannel = RawAudioRecordWrapper.RawAudioOutputChannel.ORIGINAL
    )
    private val rawRecorderCallback = object : RawAudioRecordWrapper.IRawAudioRecorder {
        override fun onReceivedPcmData(pcmData: ByteArray) {
            Log.i(TAG, pcmData.size.toString())
        }

        override fun onFailed(throwable: Throwable) {
            Log.w(TAG, throwable.message.toString())
        }
    }

    // xfe
    private val simpleXfeRecord by lazy { SimpleXfeRecord(this) }
    private val xfeFile: File
        get() = File(
            this.getExternalFilesDir(null), "XFE_1ch_48kHz_${
                SimpleDateFormat("yyyy-MM-dd-hh-mm-ss", Locale.getDefault()).format(
                    Date()
                )
            }.raw"
        )

    // volume
    private val volumeCtrl: VolumeControl by lazy {
        VolumeControl(
            context = this,
            lifecycle = this.lifecycle
        )
    }
    private val volumeReceiver: IVolumeReceiver by lazy {
        VolumeReceiverImpl(
            context = this,
            lifecycle = this.lifecycle
        )
    }

    // gesture
    private val gestureSensorManager =
        GestureSensorManager(object : GestureSensorEventCallback {
            override fun onGestureUpToDown() {
                Log.i(TAG, "onGestureUpToDown")
                binding.textviewGesture.text = "⇣"
            }

            override fun onGestureDownToUp() {
                Log.i(TAG, "onGestureDownToUp")
                binding.textviewGesture.text = "⇡"
            }

            override fun onGestureRightToLeft() {
                Log.i(TAG, "onGestureRightToLeft")
                binding.textviewGesture.text = "⇠"
            }

            override fun onGestureLeftToRight() {
                Log.i(TAG, "onGestureLeftToRight")
                binding.textviewGesture.text = "⇢"
            }
        })

    // Wearing
    @OptIn(ExperimentalFeature::class)
    private var wearSensorManager: WearSensorManager? = null

    @SuppressLint("SetTextI18n")
    @OptIn(ExperimentalFeature::class)
    private val wearEventListener =
        object : WearSensorManager.IProximityEvent, WearSensorManager.IGyroscopeEvent {
            override fun onMounted() {
                runOnUiThread {
                    binding.textviewWearing.text = "onMounted"
                }
            }

            override fun onRemoved() {
                runOnUiThread {
                    binding.textviewWearing.text = "onRemoved"
                }
            }

            override fun onSideBlurred() {}

            override fun onUpDownBlurred() {
                Toast.makeText(this@MainActivity, "onUpDownBlurred", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dumpVersions()
        registerEvent()
    }

    @OptIn(ExperimentalFeature::class)
    override fun onResume() {
        super.onResume()
        gestureSensorManager.startTracking(this)

        if (wearSensorManager == null) {
            wearSensorManager = WearSensorManager(this)
        }
        wearSensorManager?.startTracking(wearEventListener, wearEventListener)

        if (this.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO
            )
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        }

    }

    @OptIn(ExperimentalFeature::class)
    override fun onPause() {
        super.onPause()
        gestureSensorManager.stopTracking()
        wearSensorManager?.stopTracking()
        wearSensorManager = null
    }

    @SuppressLint("MissingPermission")
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            // 第1キー（アーム先端側のボタン）
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                // マイクゲインを最大に上げる
                if (!MicGainControl(this).micGain(20)) {
                    return false
                }
                if (!rawRecorder.prepare(this)) {
                    return false
                }
                LedClient(this).updateCameraLed(true)
                rawRecorder.start(rawFileOutputStream, rawRecorderCallback)
                Toast.makeText(this, "--- Start Recording ---", Toast.LENGTH_SHORT).show()
                Log.v(TAG, "--- Start Recording ---")
            }
            // 第2キー（中央ボタン）
            KeyEvent.KEYCODE_CAMERA -> {
                val adbClient = AdbClient(this)
                adbClient.disableAsync().thenRun {
                    Handler(Looper.getMainLooper()).postDelayed({
                        adbClient.enableAsync()
                    }, 1000)
                }
            }
            // 第3キー（アーム先端から最も遠いボタン）
            KeyEvent.KEYCODE_VOLUME_UP -> {
                LedClient(this).updateCameraLed(false)
                rawRecorder.stop()
                // マイクゲインをリセットする
                MicGainControl(this).resetMicGain()
                Toast.makeText(this, "--- Stop Recording ---", Toast.LENGTH_SHORT).show()
                Log.v(TAG, "--- Stop Recording ---")
            }

            KeyEvent.KEYCODE_POWER -> {
                event?.startTracking()
            }
        }
        return true
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_POWER -> {
                PowerController().reboot(this)
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
            }

            else -> {}
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun launcherExtensionSwitchDialog() {
        AlertDialog.Builder(this)
            .setTitle("LauncherExtension Enabled?")
            .setPositiveButton("Enable") { _, _ ->
                Extension().enableAutoLaunchMode()
            }
            .setNegativeButton("Disable") { _, _ ->
                Extension().disableAutoLaunchMode()
            }
            .show()
    }

    private fun registerEvent() {
        // Launcher Extension //
        binding.buttonExtensionDefault.setOnClickListener {
            Extension().configure(
                "com.android.deskclock",
                "com.android.deskclock.DeskClock"
            )
            launcherExtensionSwitchDialog()
            Toast.makeText(this, "${Extension().configure()}", Toast.LENGTH_SHORT).show()
        }

        binding.buttonExtensionThisApp.setOnClickListener {
            Extension().configure(
                this.packageName,
                "${this.packageName}.${MainActivity::class.java.simpleName}"
            )
            launcherExtensionSwitchDialog()
            Toast.makeText(this, "${Extension().configure()}", Toast.LENGTH_SHORT).show()
        }

        // Camera angle //
        val angles = listOf("0", "90", "180", "270")
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, angles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCameraAngles.adapter = adapter

        val idx = angles.indexOf(Angle().current().toString())
        if (idx == -1) {
            binding.spinnerCameraAngles.setSelection(1)
        } else {
            binding.spinnerCameraAngles.setSelection(idx)
        }

        binding.buttonCameraAngle.setOnClickListener {
            when (angles[binding.spinnerCameraAngles.selectedItemPosition]) {
                "0" -> Angle().rotate0()
                "90" -> Angle().rotate90()
                "180" -> Angle().rotate180()
                "270" -> Angle().rotate270()
                else -> {}
            }
            Toast.makeText(this, Angle().current().toString(), Toast.LENGTH_SHORT).show()
        }

        binding.buttonCameraPortrait.setOnClickListener {
            Angle().setPortrait()
            if (!Angle().isPortrait()) {
                return@setOnClickListener
            }
            val ang = Angle().current().toString()
            binding.spinnerCameraAngles.setSelection(angles.indexOf(ang))
            Toast.makeText(this, ang, Toast.LENGTH_SHORT).show()
        }

        binding.buttonCameraLandscape.setOnClickListener {
            Angle().setLandscape()
            if (!Angle().isLandscape()) {
                return@setOnClickListener
            }
            val ang = Angle().current().toString()
            binding.spinnerCameraAngles.setSelection(angles.indexOf(ang))
            Toast.makeText(this, ang, Toast.LENGTH_SHORT).show()
        }

        // Language //
        val langClient = Language(this)
        val locales = langClient.getAvailableLocales()
        val adapterLang =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                locales.map { it.displayLanguage })
        adapterLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLanguages.adapter = adapterLang
        val id = locales.indexOf(Locale.getDefault())
        if (id > 0) {
            binding.spinnerLanguages.setSelection(id)
        }
        binding.buttonUpdateLanguage.setOnClickListener {
            langClient.updateRequest(locales[binding.spinnerLanguages.selectedItemPosition])
        }

        // Power //
        binding.buttonReboot.setOnClickListener {
            PowerController().reboot(this)
        }

        binding.buttonShutdown.setOnClickListener {
            PowerController().shutdown(this)
        }

        // volume //
        volumeReceiver.enable()
        volumeCtrl.enable()
        binding.seekVolume.isEnabled = false
        lifecycleScope.launch {
            volumeReceiver.volumeFlow.collect {
                binding.seekVolume.progress = it
            }
        }
        binding.buttonVolumeStepUp.setOnClickListener {
            volumeCtrl.stepUp()
        }
        binding.buttonVolumeStepDown.setOnClickListener {
            volumeCtrl.stepDown()
        }

        // xfe //
        val scope = lifecycleScope
        binding.buttonXfeRecord.setOnClickListener {
            binding.buttonXfeRecord.isEnabled = false
            scope.launch {
                val file = xfeFile
                file.createNewFile()
                val result = simpleXfeRecord.tryRecord(file)
                Toast.makeText(
                    this@MainActivity,
                    "XFE record=${result.name} file=${file.absoluteFile}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.buttonXfeRecord.isEnabled = true
            }
        }
    }

    private fun dumpVersions() {
        val msg =
            "${ai.fd.thinklet.sdk.audio.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.audio.BuildConfig.VERSION}" + "\n" +
                    "${ai.fd.thinklet.sdk.gesture.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.gesture.BuildConfig.VERSION}" + "\n" +
                    "${ai.fd.thinklet.sdk.led.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.led.BuildConfig.VERSION}" + "\n" +
                    "${ai.fd.thinklet.sdk.maintenance.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.maintenance.BuildConfig.VERSION}"
        Log.v(TAG, msg)
    }
}
