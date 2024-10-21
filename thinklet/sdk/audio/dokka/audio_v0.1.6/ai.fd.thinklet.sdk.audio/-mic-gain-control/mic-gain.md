//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[MicGainControl](index.md)/[micGain](mic-gain.md)

# micGain

[androidJvm]\
fun [micGain](mic-gain.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Return input mic gain config.

#### Return

gain. default:12，min:0，max:20

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) | firmware is not supported. |
| [RuntimeException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) | internal error. |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.MODIFY_AUDIO_SETTINGS&quot;)

fun [micGain](mic-gain.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Update input mic gain config.

#### Return

update result.

#### Parameters

androidJvm

| | |
|---|---|
| level | new gain. min:0，max:20 |

#### Throws

| | |
|---|---|
| [UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) | firmware is not supported. |
