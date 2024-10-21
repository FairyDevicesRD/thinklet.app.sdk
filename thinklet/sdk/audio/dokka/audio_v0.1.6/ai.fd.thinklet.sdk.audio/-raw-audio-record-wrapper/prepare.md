//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[RawAudioRecordWrapper](index.md)/[prepare](prepare.md)

# prepare

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.RECORD_AUDIO&quot;)

fun [prepare](prepare.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Prepare for AudioRecord. Returns false if permission RECORD_AUDIO is not allowed or any reasons.

#### Return

Boolean Return true only when ready

#### Parameters

androidJvm

| | |
|---|---|
| context | Context |
