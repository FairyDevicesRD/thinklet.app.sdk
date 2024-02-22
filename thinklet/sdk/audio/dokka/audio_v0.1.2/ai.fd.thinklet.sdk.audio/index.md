//[audio_v0.1.2](../../index.md)/[ai.fd.thinklet.sdk.audio](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [MicGainControl](-mic-gain-control/index.md) | [androidJvm]<br>class [MicGainControl](-mic-gain-control/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Gain control API for 5 microphones on THINKLET. |
| [MultiChannelAudioRecord](-multi-channel-audio-record/index.md) | [androidJvm]<br>class [MultiChannelAudioRecord](-multi-channel-audio-record/index.md)<br>Provides multi-mic AudioRecord for THINKLET The bit depth is fixed at 16bit. (AudioFormat.ENCODING_PCM_16BIT) |
| [RawAudioRecordWrapper](-raw-audio-record-wrapper/index.md) | [androidJvm]<br>class [RawAudioRecordWrapper](-raw-audio-record-wrapper/index.md)(channel: [MultiChannelAudioRecord.Channel](-multi-channel-audio-record/-channel/index.md) = Channel.CHANNEL_SIX, sampleRate: [MultiChannelAudioRecord.SampleRate](-multi-channel-audio-record/-sample-rate/index.md) = SampleRate.SAMPLING_RATE_48000, outputChannel: [RawAudioRecordWrapper.RawAudioOutputChannel](-raw-audio-record-wrapper/-raw-audio-output-channel/index.md) = RawAudioOutputChannel.ORIGINAL)<br>A wrapper class for recording using AudioRecord with a sampling rate of 48kHz and 6ch (5ch). |
| [SystemSound](-system-sound/index.md) | [androidJvm]<br>class [SystemSound](-system-sound/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Control THINKLET system sounds. |
| [VolumeControl](-volume-control/index.md) | [androidJvm]<br>class [VolumeControl](-volume-control/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html))<br>Device volume control. Change volume of supported streams in 4 steps. |
