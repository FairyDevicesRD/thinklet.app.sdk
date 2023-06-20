//[audio_v0.0.4](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[MicGainControl](index.md)/[micPeakControlGain](mic-peak-control-gain.md)

# micPeakControlGain

[androidJvm]\
fun [micPeakControlGain](mic-peak-control-gain.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Return peak control gain config.

#### Return

peak control gain. default:84, min:0, max:124

## Throws

| | |
|---|---|
| [kotlin.UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) | firmware is not supported. |
| [kotlin.RuntimeException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) | internal error. |

[androidJvm]\
fun [micPeakControlGain](mic-peak-control-gain.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Update peak control gain config.

#### Return

update result

## Parameters

androidJvm

| | |
|---|---|
| level | new peak control gain.  min:0, max:124. |

## Throws

| | |
|---|---|
| [kotlin.UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) | firmware is not supported. |
