//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[MultiChannelAudioRecord](index.md)

# MultiChannelAudioRecord

[androidJvm]\
class [MultiChannelAudioRecord](index.md)

Provides multi-mic AudioRecord for THINKLET The bit depth is fixed at 16bit. (AudioFormat.ENCODING_PCM_16BIT)

## Constructors

| | |
|---|---|
| [MultiChannelAudioRecord](-multi-channel-audio-record.md) | [androidJvm]<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [AudioRecordWithBufferSize](-audio-record-with-buffer-size/index.md) | [androidJvm]<br>data class [AudioRecordWithBufferSize](-audio-record-with-buffer-size/index.md)(val audioRecord: [AudioRecord](https://developer.android.com/reference/kotlin/android/media/AudioRecord.html), val bufferSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>AudioRecordWithBufferSize provides AudioRecord and the buffer size used for recording |
| [Channel](-channel/index.md) | [androidJvm]<br>enum [Channel](-channel/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[MultiChannelAudioRecord.Channel](-channel/index.md)&gt; <br>Channel count. |
| [SampleRate](-sample-rate/index.md) | [androidJvm]<br>enum [SampleRate](-sample-rate/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[MultiChannelAudioRecord.SampleRate](-sample-rate/index.md)&gt; <br>SamplingRate. |

## Functions

| Name | Summary |
|---|---|
| [get](get.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)<br>fun [~~get~~](get.md)(): [AudioRecord](https://developer.android.com/reference/kotlin/android/media/AudioRecord.html)<br>Get an AudioRecord object (6ch/48kHz) The bit depth is fixed at 16bit. (AudioFormat.ENCODING_PCM_16BIT)<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)<br>fun [get](get.md)(channel: [MultiChannelAudioRecord.Channel](-channel/index.md), sampleRate: [MultiChannelAudioRecord.SampleRate](-sample-rate/index.md)): [MultiChannelAudioRecord.AudioRecordWithBufferSize](-audio-record-with-buffer-size/index.md)<br>Get an AudioRecord object The bit depth is fixed at 16bit. (AudioFormat.ENCODING_PCM_16BIT) |
