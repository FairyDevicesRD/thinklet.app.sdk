//[audio_v0.1.6](../../index.md)/[ai.fd.thinklet.sdk.audio](index.md)/[configureXfe](configure-xfe.md)

# configureXfe

[androidJvm]\
fun [AudioManager](https://developer.android.com/reference/kotlin/android/media/AudioManager.html).[configureXfe](configure-xfe.md)(xfeEnable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includeBtSco: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Configure XFE.

#### Return

success=true, failure=false

#### Parameters

androidJvm

| | |
|---|---|
| xfeEnable | if true, XFE is enabled. if false, XFE is disabled. |
| includeBtSco | Optional. Default is false. Uses built-in microphone instead of the Bluetooth microphone, if false, XFE will not work. |
