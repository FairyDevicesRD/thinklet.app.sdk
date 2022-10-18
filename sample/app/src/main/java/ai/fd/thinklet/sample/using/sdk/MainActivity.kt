package ai.fd.thinklet.sample.using.sdk

import ai.fd.thinklet.sdk.audio.RawAudioRecordWrapper
import ai.fd.thinklet.sdk.gesture.GestureSensorEventCallback
import ai.fd.thinklet.sdk.gesture.GestureSensorManager
import ai.fd.thinklet.sdk.led.LedClient
import ai.fd.thinklet.sdk.maintenance.adb.AdbClient
import ai.fd.thinklet.sdk.maintenance.camera.Angle
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
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private companion object {
        private const val TAG = "${BuildConfig.APPLICATION_ID}_SDK_Sample"
        private const val REQUEST_CODE = 1000
    }

    private val buttonExtensionDefault
        get() = findViewById<Button>(R.id.button_extension_default)
    private val buttonExtensionThisApp
        get() = findViewById<Button>(R.id.button_extension_this_app)

    private val spinnerCameraAngles
        get() = findViewById<Spinner>(R.id.spinner_camera_angles)
    private val buttonCameraUpdate
        get() = findViewById<Button>(R.id.button_camera_angle)

    private val textviewGesture
        get() = findViewById<TextView>(R.id.textview_gesture)

    private val buttonShutdown
        get() = findViewById<Button>(R.id.button_shutdown)
    private val buttonReboot
        get() = findViewById<Button>(R.id.button_reboot)

    // audio
    private val randomFileName: String
        get() = "6ch_48kHz_${SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Date())}.raw"
    private val rawFileOutputStream: FileOutputStream
        get() = FileOutputStream(File(this.getExternalFilesDir(null), randomFileName))
    private var rawRecorder = RawAudioRecordWrapper()
    private val rawRecorderCallback = object : RawAudioRecordWrapper.IRawAudioRecorder {
        override fun onReceivedPcmData(pcmData: ByteArray) {
            Log.i(TAG, pcmData.size.toString())
        }

        override fun onFailed(throwable: Throwable) {
            Log.w(TAG, throwable.message.toString())
        }
    }

    // gesture
    private val gestureSensorManager =
        GestureSensorManager(object : GestureSensorEventCallback {
            override fun onGestureUpToDown() {
                Log.i(TAG, "onGestureUpToDown")
                textviewGesture.text = "⇣"
            }

            override fun onGestureDownToUp() {
                Log.i(TAG, "onGestureDownToUp")
                textviewGesture.text = "⇡"
            }

            override fun onGestureRightToLeft() {
                Log.i(TAG, "onGestureRightToLeft")
                textviewGesture.text = "⇠"
            }

            override fun onGestureLeftToRight() {
                Log.i(TAG, "onGestureLeftToRight")
                textviewGesture.text = "⇢"
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dumpVersions()
        registerEvent()
    }

    override fun onResume() {
        super.onResume()
        gestureSensorManager.startTracking(this)

        if (this.checkSelfPermission(android.Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO
            )
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }

    }

    override fun onPause() {
        super.onPause()
        gestureSensorManager.stopTracking()
    }

    @SuppressLint("MissingPermission")
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            // 第1キー
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                if (!rawRecorder.prepare(this)) {
                    return false
                }
                LedClient(this).updateCameraLed(true)
                rawRecorder.start(rawFileOutputStream, rawRecorderCallback)
                Toast.makeText(this, "--- Start Recording ---", Toast.LENGTH_SHORT).show()
                Log.v(TAG, "--- Start Recording ---")
            }
            // 第２キー
            KeyEvent.KEYCODE_CAMERA -> {
                val adbClient = AdbClient(this)
                adbClient.disableAsync().thenRun {
                    Handler(Looper.getMainLooper()).postDelayed({
                        adbClient.enableAsync()
                    }, 1000)
                }
            }
            // 第3キー
            KeyEvent.KEYCODE_VOLUME_UP -> {
                LedClient(this).updateCameraLed(false)
                rawRecorder.stop()
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
        buttonExtensionDefault.setOnClickListener {
            Extension().configure(
                "com.android.deskclock",
                "com.android.deskclock.DeskClock"
            )
            launcherExtensionSwitchDialog()
            Toast.makeText(this, "${Extension().configure()}", Toast.LENGTH_SHORT).show()
        }

        buttonExtensionThisApp.setOnClickListener {
            Extension().configure(
                this.packageName,
                "${this.packageName}.${MainActivity::class.java.simpleName}"
            )
            launcherExtensionSwitchDialog()
            Toast.makeText(this, "${Extension().configure()}", Toast.LENGTH_SHORT).show()
        }

        val angles = listOf<String>("0", "90", "180", "270")
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, angles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCameraAngles.adapter = adapter

        val idx = angles.indexOf(Angle().current().toString())
        if (idx == -1) {
            spinnerCameraAngles.setSelection(1)
        } else {
            spinnerCameraAngles.setSelection(idx)
        }

        buttonCameraUpdate.setOnClickListener {
            when (angles[spinnerCameraAngles.selectedItemPosition]) {
                "0" -> Angle().rotate0()
                "90" -> Angle().rotate90()
                "180" -> Angle().rotate180()
                "270" -> Angle().rotate270()
                else -> {}
            }
            Toast.makeText(this, Angle().current().toString(), Toast.LENGTH_SHORT).show()
        }

        buttonReboot.setOnClickListener {
            PowerController().reboot(this)
        }

        buttonShutdown.setOnClickListener {
            PowerController().shutdown(this)
        }
    }

    private fun dumpVersions() {
        var msg =
            "${ai.fd.thinklet.sdk.audio.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.audio.BuildConfig.VERSION}" + "\n" +
                    "${ai.fd.thinklet.sdk.gesture.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.gesture.BuildConfig.VERSION}" + "\n" +
                    "${ai.fd.thinklet.sdk.led.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.led.BuildConfig.VERSION}" + "\n" +
                    "${ai.fd.thinklet.sdk.maintenance.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.maintenance.BuildConfig.VERSION}"
        Log.v(TAG, msg);
    }
}
