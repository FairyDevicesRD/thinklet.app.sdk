//[audio_v0.1.2](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[VolumeControl](index.md)

# VolumeControl

class [VolumeControl](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html))

Device volume control. Change volume of supported streams in 4 steps.

- 
   STREAM_VOICE_CALL
- 
   STREAM_MUSIC
- 
   STREAM_ALARM
- 
   STREAM_ACCESSIBILITY

#### Parameters

androidJvm

| | |
|---|---|
| context | Context |
| lifecycle | Lifecycle |

## Constructors

| | |
|---|---|
| [VolumeControl](-volume-control.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html)) |

## Functions

| Name | Summary |
|---|---|
| [enable](enable.md) | [androidJvm]<br>fun [enable](enable.md)()<br>Set up in the constructor lifecycle. This method must be called on the main thread. |
| [stepDown](step-down.md) | [androidJvm]<br>fun [stepDown](step-down.md)(enableLoop: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)<br>Volume down step by step. |
| [stepUp](step-up.md) | [androidJvm]<br>fun [stepUp](step-up.md)(enableLoop: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)<br>Volume up step by step. |
