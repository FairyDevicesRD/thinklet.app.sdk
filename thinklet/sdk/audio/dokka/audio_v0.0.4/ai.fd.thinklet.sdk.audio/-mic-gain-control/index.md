//[audio_v0.0.4](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[MicGainControl](index.md)

# MicGainControl

[androidJvm]\
class [MicGainControl](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Gain control API for 5 microphones on THINKLET.

## Parameters

androidJvm

| | |
|---|---|
| context | Context |

## Constructors

| | |
|---|---|
| [MicGainControl](-mic-gain-control.md) | [androidJvm]<br>fun [MicGainControl](-mic-gain-control.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [micGain](mic-gain.md) | [androidJvm]<br>fun [micGain](mic-gain.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Return input mic gain config.<br>[androidJvm]<br>fun [micGain](mic-gain.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Update input mic gain config. |
| [micPeakControlGain](mic-peak-control-gain.md) | [androidJvm]<br>fun [micPeakControlGain](mic-peak-control-gain.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Return peak control gain config.<br>[androidJvm]<br>fun [micPeakControlGain](mic-peak-control-gain.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Update peak control gain config. |
| [resetMicGain](reset-mic-gain.md) | [androidJvm]<br>fun [resetMicGain](reset-mic-gain.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Reset input mic gain config. |
| [resetMicPeakControlGain](reset-mic-peak-control-gain.md) | [androidJvm]<br>fun [resetMicPeakControlGain](reset-mic-peak-control-gain.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Reset peak control gain config. |
