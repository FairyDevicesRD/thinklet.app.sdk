//[maintenance_v0.0.3](../../../index.md)/[ai.fd.thinklet.sdk.maintenance.launcher](../index.md)/[Extension](index.md)

# Extension

[androidJvm]\
class [Extension](index.md)

Set the function to start automatically from ThinkletLauncher. It becomes effective automatically when the terminal is restarted.

## Constructors

| | |
|---|---|
| [Extension](-extension.md) | [androidJvm]<br>fun [Extension](-extension.md)() |

## Functions

| Name | Summary |
|---|---|
| [configure](configure.md) | [androidJvm]<br>fun [configure](configure.md)(): [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Get auto-launch settings from Launcher.<br>[androidJvm]<br>fun [configure](configure.md)(packageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), className: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Automatic launch setting from Launcher. Saved even after reboot. |
| [disableAutoLaunchMode](disable-auto-launch-mode.md) | [androidJvm]<br>fun [disableAutoLaunchMode](disable-auto-launch-mode.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Disable automatic startup |
| [enableAutoLaunchMode](enable-auto-launch-mode.md) | [androidJvm]<br>fun [enableAutoLaunchMode](enable-auto-launch-mode.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Enable automatic startup |
| [isAutoLaunchMode](is-auto-launch-mode.md) | [androidJvm]<br>fun [isAutoLaunchMode](is-auto-launch-mode.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether auto start is enabled |
