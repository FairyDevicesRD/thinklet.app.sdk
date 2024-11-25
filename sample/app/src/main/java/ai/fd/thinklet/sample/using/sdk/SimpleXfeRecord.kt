package ai.fd.thinklet.sample.using.sdk

import ai.fd.thinklet.sdk.audio.ExperimentalXfeFeature
import ai.fd.thinklet.sdk.audio.ThinkletXfe
import ai.fd.thinklet.sdk.audio.canXfeRecord
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.MediaRecorder.AudioSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@OptIn(ExperimentalXfeFeature::class)
class SimpleXfeRecord(private val context: Context) {
    enum class Result {
        FAILED_CONFIGURE,
        NO_PERMISSION,
        FAILED_TO_WRITE_FILE,
        RECORDING_ERROR,
        SUCCESS
    }

    private val am = context.getSystemService(AudioManager::class.java)
    private val xfeCtl = ThinkletXfe()

    suspend fun tryRecord(writeFile: File, repeatTimes: Int = 500): Result =
        withContext(Dispatchers.Default) {
            if (!xfeCtl.configureXfe(audioManager = am, xfeEnable = true)) {
                return@withContext Result.FAILED_CONFIGURE
            }
            if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                return@withContext Result.NO_PERMISSION
            }
            if (!writeFile.canWrite()) {
                return@withContext Result.FAILED_TO_WRITE_FILE
            }
            val audioRecord = AudioRecord.Builder()
                .setAudioSource(AudioSource.MIC)
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(48000) // or 16000
                        .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
                        .build()
                )
                .setBufferSizeInBytes(1920)
                .build()
            if (!audioRecord.canXfeRecord()) {
                audioRecord.release()
                return@withContext Result.FAILED_CONFIGURE
            }

            var result: Result
            try {
                audioRecord.startRecording()
                val buf = ByteArray(1920)
                repeat(repeatTimes) {
                    val read = audioRecord.read(buf, 0, buf.size)
                    if (read > 0) {
                        writeFile.appendBytes(buf)
                    }
                }
                result = Result.SUCCESS
            } catch (_: Exception) {
                result = Result.RECORDING_ERROR
            } finally {
                audioRecord.stop()
                audioRecord.release()
                xfeCtl.configureXfe(
                    audioManager = am,
                    xfeEnable = false
                )
            }
            return@withContext result
        }
}
