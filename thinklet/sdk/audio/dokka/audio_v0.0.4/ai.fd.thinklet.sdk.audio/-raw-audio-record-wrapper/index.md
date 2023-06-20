//[audio_v0.0.4](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[RawAudioRecordWrapper](index.md)

# RawAudioRecordWrapper

[androidJvm]\
class [RawAudioRecordWrapper](index.md)

A wrapper class for recording using AudioRecord with a sampling rate of 48kHz and 6ch (5ch).

## Constructors

| | |
|---|---|
| [RawAudioRecordWrapper](-raw-audio-record-wrapper.md) | [androidJvm]<br>fun [RawAudioRecordWrapper](-raw-audio-record-wrapper.md)() |

## Types

| Name | Summary |
|---|---|
| [IRawAudioRecorder](-i-raw-audio-recorder/index.md) | [androidJvm]<br>interface [IRawAudioRecorder](-i-raw-audio-recorder/index.md)<br>Callback interface called when PCM data is received. |

## Functions

| Name | Summary |
|---|---|
| [prepare](prepare.md) | [androidJvm]<br>fun [prepare](prepare.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Prepare for AudioRecord. Returns false if permission RECORD_AUDIO is not allowed or any reasons. |
| [start](start.md) | [androidJvm]<br>fun [start](start.md)(outputStream: [OutputStream](https://developer.android.com/reference/kotlin/java/io/OutputStream.html)?, callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?)<br>Start recording (enableEchoCanceler is enable)<br>[androidJvm]<br>fun [start](start.md)(outputStream: [OutputStream](https://developer.android.com/reference/kotlin/java/io/OutputStream.html)?, callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?, enableEchoCanceler: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Start recording |
| [stop](stop.md) | [androidJvm]<br>fun [stop](stop.md)()<br>Stop recording |
