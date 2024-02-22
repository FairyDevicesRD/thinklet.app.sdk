//[audio_v0.1.2](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[RawAudioRecordWrapper](index.md)

# RawAudioRecordWrapper

class [RawAudioRecordWrapper](index.md)(channel: [MultiChannelAudioRecord.Channel](../-multi-channel-audio-record/-channel/index.md) = Channel.CHANNEL_SIX, sampleRate: [MultiChannelAudioRecord.SampleRate](../-multi-channel-audio-record/-sample-rate/index.md) = SampleRate.SAMPLING_RATE_48000, outputChannel: [RawAudioRecordWrapper.RawAudioOutputChannel](-raw-audio-output-channel/index.md) = RawAudioOutputChannel.ORIGINAL)

A wrapper class for recording using AudioRecord with a sampling rate of 48kHz and 6ch (5ch).

#### Parameters

androidJvm

| | |
|---|---|
| channel | default 6ch |
| sampleRate | default 48kHz |
| outputChannel | default ORIGINAL |

## Constructors

| | |
|---|---|
| [RawAudioRecordWrapper](-raw-audio-record-wrapper.md) | [androidJvm]<br>constructor(channel: [MultiChannelAudioRecord.Channel](../-multi-channel-audio-record/-channel/index.md) = Channel.CHANNEL_SIX, sampleRate: [MultiChannelAudioRecord.SampleRate](../-multi-channel-audio-record/-sample-rate/index.md) = SampleRate.SAMPLING_RATE_48000, outputChannel: [RawAudioRecordWrapper.RawAudioOutputChannel](-raw-audio-output-channel/index.md) = RawAudioOutputChannel.ORIGINAL) |

## Types

| Name | Summary |
|---|---|
| [IRawAudioRecorder](-i-raw-audio-recorder/index.md) | [androidJvm]<br>interface [IRawAudioRecorder](-i-raw-audio-recorder/index.md)<br>Callback interface called when PCM data is received. |
| [RawAudioOutputChannel](-raw-audio-output-channel/index.md) | [androidJvm]<br>enum [RawAudioOutputChannel](-raw-audio-output-channel/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[RawAudioRecordWrapper.RawAudioOutputChannel](-raw-audio-output-channel/index.md)&gt; <br>Number of channels to output |

## Functions

| Name | Summary |
|---|---|
| [prepare](prepare.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)<br>fun [prepare](prepare.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Prepare for AudioRecord. Returns false if permission RECORD_AUDIO is not allowed or any reasons. |
| [start](start.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)<br>fun [start](start.md)(outputStream: [OutputStream](https://developer.android.com/reference/kotlin/java/io/OutputStream.html)?, callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?)<br>Start recording (enableEchoCanceler is enable)<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)<br>fun [start](start.md)(outputStream: [OutputStream](https://developer.android.com/reference/kotlin/java/io/OutputStream.html)?, callback: [RawAudioRecordWrapper.IRawAudioRecorder](-i-raw-audio-recorder/index.md)?, enableEchoCanceler: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Start recording |
| [stop](stop.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)<br>fun [stop](stop.md)()<br>Stop recording |
