//[audio_v0.1.6](../../../index.md)/[ai.fd.thinklet.sdk.audio](../index.md)/[RawAudioRecordWrapper](index.md)/[getTimestamp](get-timestamp.md)

# getTimestamp

[androidJvm]\
fun [getTimestamp](get-timestamp.md)(outTimestamp: [AudioTimestamp](https://developer.android.com/reference/kotlin/android/media/AudioTimestamp.html), timebase: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Call Android AudioRecord#getTimestamp.

#### Return

AudioRecord.SUCCESS if a timestamp is available, or AudioRecord.ERROR_INVALID_OPERATION if a timestamp not available.

#### Parameters

androidJvm

| | |
|---|---|
| outTimestamp | provided AudioTimestamp instance |
| timebase | TIMEBASE_BOOTTIME or TIMEBASE_MONOTONIC |
