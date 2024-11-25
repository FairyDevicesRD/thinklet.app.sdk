//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[ThinkletXfe](index.md)

# ThinkletXfe

[androidJvm]\
class [ThinkletXfe](index.md)

Change XFE mode control API.

- 
   [XFE](https://mimi.fairydevices.jp/technology/edge/xfe/)

## Constructors

| | |
|---|---|
| [ThinkletXfe](-thinklet-xfe.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [canXfeRecord](can-xfe-record.md) | [androidJvm]<br>fun [canXfeRecord](can-xfe-record.md)(audioRecord: [AudioRecord](https://developer.android.com/reference/kotlin/android/media/AudioRecord.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Check if XFE is available. |
| [configureXfe](configure-xfe.md) | [androidJvm]<br>fun [configureXfe](configure-xfe.md)(audioManager: [AudioManager](https://developer.android.com/reference/kotlin/android/media/AudioManager.html), xfeEnable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includeBtSco: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Configure XFE. |
