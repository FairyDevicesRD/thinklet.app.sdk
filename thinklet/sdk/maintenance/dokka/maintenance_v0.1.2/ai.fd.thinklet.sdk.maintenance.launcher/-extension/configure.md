//[maintenance_v0.1.2](../../../index.md)/[ai.fd.thinklet.sdk.maintenance.launcher](../index.md)/[Extension](index.md)/[configure](configure.md)

# configure

[androidJvm]\
fun [configure](configure.md)(packageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), className: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Automatic launch setting from Launcher. Saved even after reboot.

#### Parameters

androidJvm

| | |
|---|---|
| packageName | ex. ai.fd.thinklet.sample.test |
| className | ex. ai.fd.thinklet.sample.test.ui.MainActivity |

#### Throws

| |
|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) |

[androidJvm]\
fun [configure](configure.md)(): [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

Get auto-launch settings from Launcher.

#### Return

Pair<Package name, Class name>

#### Throws

| |
|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) |
