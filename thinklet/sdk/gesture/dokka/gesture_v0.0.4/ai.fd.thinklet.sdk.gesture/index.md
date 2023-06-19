//[gesture_v0.0.4](../../index.md)/[ai.fd.thinklet.sdk.gesture](index.md)

# Package ai.fd.thinklet.sdk.gesture

## Types

| Name | Summary |
|---|---|
| [ExperimentalFeature](-experimental-feature/index.md) | [androidJvm]<br>@[Target](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.annotation/-target/index.html)(allowedTargets = [[AnnotationTarget.CLASS](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.annotation/-annotation-target/-c-l-a-s-s/index.html)])<br>annotation class [ExperimentalFeature](-experimental-feature/index.md) |
| [GestureSensorEventCallback](-gesture-sensor-event-callback/index.md) | [androidJvm]<br>interface [GestureSensorEventCallback](-gesture-sensor-event-callback/index.md)<br>Direction to move your hand when wearing |
| [GestureSensorManager](-gesture-sensor-manager/index.md) | [androidJvm]<br>class [GestureSensorManager](-gesture-sensor-manager/index.md)(callback: [GestureSensorEventCallback](-gesture-sensor-event-callback/index.md))<br>Event receiver class from the gesture sensor. |
| [WearSensorManager](-wear-sensor-manager/index.md) | [androidJvm]<br>class [WearSensorManager](-wear-sensor-manager/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), config: [WearSensorManager.Config](-wear-sensor-manager/-config/index.md) = Config()) : [SensorEventListener](https://developer.android.com/reference/kotlin/android/hardware/SensorEventListener.html)<br>Calculate proximity sensor and gyro sensor results as wearing events. |
