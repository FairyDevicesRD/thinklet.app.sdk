//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[RawAudioRecordWrapper](index.md)/[start](start.md)

# start

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)

fun [start](start.md)(outputStream: [OutputStream](https://developer.android.com/reference/kotlin/java/io/OutputStream.html)?, callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?)

Start recording (enableEchoCanceler is enable)

#### Parameters

androidJvm

| | |
|---|---|
| outputStream | Output Stream |
| callback | IRawAudioRecorder object |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)

fun [start](start.md)(callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?, enableEchoCanceler: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Start recording

#### Parameters

androidJvm

| | |
|---|---|
| callback | IRawAudioRecorder object |
| enableEchoCanceler | (supported 6ch only) |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)

fun [start](start.md)(outputStream: [OutputStream](https://developer.android.com/reference/kotlin/java/io/OutputStream.html)?, callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?, enableEchoCanceler: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Start recording

#### Parameters

androidJvm

| | |
|---|---|
| outputStream | Output Stream |
| callback | IRawAudioRecorder object |
| enableEchoCanceler | (supported 6ch only) |
