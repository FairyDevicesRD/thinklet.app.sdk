//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[MicGainControl](index.md)/[micPeakControlGain](mic-peak-control-gain.md)

# micPeakControlGain

[androidJvm]\
fun [micPeakControlGain](mic-peak-control-gain.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Return peak control gain config.

#### Return

peak control gain. default:84, min:0, max:124

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) | firmware is not supported. |
| [RuntimeException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) | internal error. |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.MODIFY_AUDIO_SETTINGS&quot;)

fun [micPeakControlGain](mic-peak-control-gain.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Update peak control gain config.

#### Return

update result

#### Parameters

androidJvm

| | |
|---|---|
| level | new peak control gain.  min:0, max:124. |

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) | firmware is not supported. |
