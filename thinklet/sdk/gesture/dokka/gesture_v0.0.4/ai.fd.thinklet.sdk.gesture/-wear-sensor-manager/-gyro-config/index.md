//[gesture_v0.0.4](../../../../index.md)/[ai.fd.thinklet.sdk.gesture](../../index.md)/[WearSensorManager](../index.md)/[GyroConfig](index.md)

# GyroConfig

[androidJvm]\
data class [GyroConfig](index.md)(val sampleData: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 20, val sensitivity: [WearSensorManager.GyroSensitivity](../-gyro-sensitivity/index.md) = GyroSensitivity.MIDDLE)

Gyro sensor config

## Parameters

androidJvm

| | |
|---|---|
| sampleData | Total population for variance calculation. default:20 |
| sensitivity | Sensitivity level to judge that it is shaking. default:middle |

## Constructors

| | |
|---|---|
| [GyroConfig](-gyro-config.md) | [androidJvm]<br>fun [GyroConfig](-gyro-config.md)(sampleData: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 20, sensitivity: [WearSensorManager.GyroSensitivity](../-gyro-sensitivity/index.md) = GyroSensitivity.MIDDLE) |

## Functions

| Name | Summary |
|---|---|
| [sampleData](sample-data.md) | [androidJvm]<br>fun [sampleData](sample-data.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Properties

| Name | Summary |
|---|---|
| [sampleData](sample-data.md) | [androidJvm]<br>val [sampleData](sample-data.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 20 |
| [sensitivity](sensitivity.md) | [androidJvm]<br>val [sensitivity](sensitivity.md): [WearSensorManager.GyroSensitivity](../-gyro-sensitivity/index.md) |
