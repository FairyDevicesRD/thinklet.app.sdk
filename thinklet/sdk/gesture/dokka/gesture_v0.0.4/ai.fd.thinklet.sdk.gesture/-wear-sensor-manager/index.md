//[gesture_v0.0.4](../../../index.md)/[ai.fd.thinklet.sdk.gesture](../index.md)/[WearSensorManager](index.md)

# WearSensorManager

[androidJvm]\
class [WearSensorManager](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), config: [WearSensorManager.Config](-config/index.md) = Config()) : [SensorEventListener](https://developer.android.com/reference/kotlin/android/hardware/SensorEventListener.html)

Calculate proximity sensor and gyro sensor results as wearing events.

## Parameters

androidJvm

| | |
|---|---|
| context | Context |
| config | Sensor configure. |

## Constructors

| | |
|---|---|
| [WearSensorManager](-wear-sensor-manager.md) | [androidJvm]<br>fun [WearSensorManager](-wear-sensor-manager.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), config: [WearSensorManager.Config](-config/index.md) = Config()) |

## Types

| Name | Summary |
|---|---|
| [Config](-config/index.md) | [androidJvm]<br>data class [Config](-config/index.md)(val proximityConfig: [WearSensorManager.ProximityConfig](-proximity-config/index.md) = ProximityConfig(), val gyroConfig: [WearSensorManager.GyroConfig](-gyro-config/index.md) = GyroConfig())<br>Sensor configure. |
| [GyroConfig](-gyro-config/index.md) | [androidJvm]<br>data class [GyroConfig](-gyro-config/index.md)(val sampleData: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 20, val sensitivity: [WearSensorManager.GyroSensitivity](-gyro-sensitivity/index.md) = GyroSensitivity.MIDDLE)<br>Gyro sensor config |
| [GyroSensitivity](-gyro-sensitivity/index.md) | [androidJvm]<br>enum [GyroSensitivity](-gyro-sensitivity/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[WearSensorManager.GyroSensitivity](-gyro-sensitivity/index.md)&gt; <br>Gyro sensitivity level |
| [IGyroscopeEvent](-i-gyroscope-event/index.md) | [androidJvm]<br>interface [IGyroscopeEvent](-i-gyroscope-event/index.md)<br>Shaking event |
| [IProximityEvent](-i-proximity-event/index.md) | [androidJvm]<br>interface [IProximityEvent](-i-proximity-event/index.md)<br>Mounting Event |
| [ProximityConfig](-proximity-config/index.md) | [androidJvm]<br>data class [ProximityConfig](-proximity-config/index.md)(val bufferMillTime: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 250)<br>Proximity sensor Config |

## Functions

| Name | Summary |
|---|---|
| [onAccuracyChanged](on-accuracy-changed.md) | [androidJvm]<br>open override fun [onAccuracyChanged](on-accuracy-changed.md)(p0: [Sensor](https://developer.android.com/reference/kotlin/android/hardware/Sensor.html)?, p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onSensorChanged](on-sensor-changed.md) | [androidJvm]<br>open override fun [onSensorChanged](on-sensor-changed.md)(event: [SensorEvent](https://developer.android.com/reference/kotlin/android/hardware/SensorEvent.html)?) |
| [startTracking](start-tracking.md) | [androidJvm]<br>fun [startTracking](start-tracking.md)(proximityEvent: [WearSensorManager.IProximityEvent](-i-proximity-event/index.md)? = null, gyroscopeEvent: [WearSensorManager.IGyroscopeEvent](-i-gyroscope-event/index.md)? = null)<br>Start tracking |
| [stopTracking](stop-tracking.md) | [androidJvm]<br>fun [stopTracking](stop-tracking.md)()<br>Stop tracking. |
