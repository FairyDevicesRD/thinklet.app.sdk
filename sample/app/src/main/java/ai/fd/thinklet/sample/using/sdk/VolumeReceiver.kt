package ai.fd.thinklet.sample.using.sdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface IVolumeReceiver {
    val volumeFlow: StateFlow<Int>
    fun enable()
}

class VolumeReceiverImpl(
    private val context: Context,
    private val lifecycle: Lifecycle
) : BroadcastReceiver(), IVolumeReceiver, DefaultLifecycleObserver {
    private val _volume: MutableStateFlow<Int> = MutableStateFlow(0)
    override val volumeFlow = _volume.asStateFlow()

    override fun enable() {
        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        _volume.value = am.getStreamVolume(AudioManager.STREAM_MUSIC)

        lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        context.registerReceiver(this, IntentFilter(ACTION))
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        context.unregisterReceiver(this)
    }

    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras ?: return
        if (extras.containsKey(EX_KEY_VALUE) && extras.containsKey(EX_KEY_TYPE)) {
            val streamType = intent.getIntExtra(EX_KEY_TYPE, -1)
            if (streamType != AudioManager.STREAM_MUSIC) return

            val vol = extras.getInt(EX_KEY_VALUE, -1)
            if (vol >= 0) _volume.value = vol
        }
    }

    private companion object {
        const val ACTION = "android.media.VOLUME_CHANGED_ACTION"
        const val EX_KEY_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE"
        const val EX_KEY_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE"
    }
}
