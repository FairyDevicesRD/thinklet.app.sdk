//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[MultiChannelAudioRecord](index.md)/[get](get.md)

# get

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)

fun [get](get.md)(channel: [MultiChannelAudioRecord.Channel](-channel/index.md), sampleRate: [MultiChannelAudioRecord.SampleRate](-sample-rate/index.md)): [MultiChannelAudioRecord.AudioRecordWithBufferSize](-audio-record-with-buffer-size/index.md)

Get an AudioRecord object The bit depth is fixed at 16bit. (AudioFormat.ENCODING_PCM_16BIT)

#### Return

AudioRecordWithBufferSize (AudioRecord + bufferSize)

#### Parameters

androidJvm

| | |
|---|---|
| channel | record channel. |
| sampleRate | record samplingRate. |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)

fun [~~get~~](get.md)(): [AudioRecord](https://developer.android.com/reference/kotlin/android/media/AudioRecord.html)

---

### Deprecated

#### Replace with

```kotlin
get(Channel.CHANNEL_SIX, SampleRate.SAMPLING_RATE_48000).audioRecord
```
---

Get an AudioRecord object (6ch/48kHz) The bit depth is fixed at 16bit. (AudioFormat.ENCODING_PCM_16BIT)

#### Return

audioRecord
