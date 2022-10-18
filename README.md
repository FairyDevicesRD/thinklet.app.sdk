- [THINKLET App SDK](#thinklet-app-sdk)
- [ご利用にあたって](#ご利用にあたって)
  - [thinklet/sdk (documents)](#thinkletsdk-documents)
  - [sample](#sample)
- [機能一覧](#機能一覧)
  - [Audio](#audio)
    - [API Document](#api-document)
    - [MultiChannelAudioRecord](#multichannelaudiorecord)
    - [RawAudioRecordWrapper](#rawaudiorecordwrapper)
  - [Camera](#camera)
    - [API Document](#api-document-1)
    - [Angle](#angle)
  - [Gesture](#gesture)
    - [API Document](#api-document-2)
    - [GestureSensorEventCallback](#gesturesensoreventcallback)
    - [GestureSensorManager](#gesturesensormanager)
  - [Launcher Extension](#launcher-extension)
    - [API Document](#api-document-3)
    - [Extension](#extension)
  - [LED](#led)
    - [API Document](#api-document-4)
    - [LedClient](#ledclient)
  - [Power](#power)
    - [API Document](#api-document-5)
    - [Power](#power-1)
  - [開発者モード](#開発者モード)
    - [API Document](#api-document-6)
    - [adb](#adb)
- [プロジェクトへの適応](#プロジェクトへの適応)
  - [Github Packages経由](#github-packages経由)
  - [更新方法](#更新方法)
- [Tips](#tips)
  - [AARのVersionの確認](#aarのversionの確認)
  - [プロジェクトをminifyEnabledする場合](#プロジェクトをminifyenabledする場合)

# THINKLET App SDK
THINKLET本体にインストールするアプリケーションに使用することができるSDKを提供します．

# ご利用にあたって
- このライブラリを許可なく再配布・再利用はしてはいけません．
- 本レポジトリに含まれるドキュメントに明示的に許可されている用途以外の用途を禁止します．
- お客様による本ライブラリの利用に関する保証を当社は行いません．
- THINKLET実機のみ動作します．

## thinklet/sdk (documents)
- ドキュメントは[CC BY-SA 4.0](./thinklet/LICENSE)の元で公開します
## sample
- サンプルソースコードは[MITライセンス](./sample/LICENSE)の元で公開します

# 機能一覧
## Audio
- 必須：`android.Manifest.permission.RECORD_AUDIO`
### API Document
- [API](./thinklet/sdk/audio/dokka/index.md)
### MultiChannelAudioRecord
- THINKLET本体の5chマイクへアクセスを行う `AudioRecord` を提供します．

```.kt
# 6ch(5ch + 1ch(=empty)), SamplingRate 48kHz
val audioRecord = MultiChannelAudioRecord().get()
```

### RawAudioRecordWrapper
- `AudioRecord`を簡易的に扱うためのクラス．
- 録音結果を出力する `OutputStream`，結果を受け取る `IRawAudioRecorder` を提供します．

```.kt
private val randomFileName: String
    get() = "6ch_48kHz_${SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Date())}.raw"
private val rawFileOutputStream: FileOutputStream
    get() = FileOutputStream(File(this.getExternalFilesDir(null), randomFileName))
private var rawRecorder = RawAudioRecordWrapper()
private val rawRecorderCallback = object : RawAudioRecordWrapper.IRawAudioRecorder {
    override fun onReceivedPcmData(pcmData: ByteArray) {
        Log.i(TAG, pcmData.size.toString())
    }

    override fun onFailed(throwable: Throwable) {
        Log.w(TAG, throwable.message.toString())
    }
}

//----

fun do() {
    if (!rawRecorder.prepare(this)) {
        return
    }
    rawRecorder.start(rawFileOutputStream, rawRecorderCallback)
}
```

## Camera
### API Document
- [API](./thinklet/sdk/maintenance/dokka/index.md)
### Angle
- `CameraCharacteristics.SENSOR_ORIENTATION` で取得できる Cameraデバイスの角度の取得，変更できます．
- デフォルトは `90` です．
- 回転すると，CameraHalが一度再起動しますので，カメラ使用中には実行しないでください．予期せぬ動作をします．

```
val camAngle = Angle()
if (camAngle.current() != "90") {
    val ret = camAngle.rotate_90()
}
```

## Gesture
### API Document
- [API](./thinklet/sdk/gesture/dokka/index.md)
### GestureSensorEventCallback
- 手を使ったジェスチャを取得，ジェスチャーセンサからのイベントコールバックを提供します．
- 関数のそれぞれは，THINKLETを装着したときの向きに合わせて，定義し，上下，左右の４つのイベントを取得できます．

### GestureSensorManager
- ジェスチャセンサを簡易的に扱うためのクラス．`GestureSensorEventCallback`のイベントを受け取れます．

```.kt
private val gestureSensorManager =
    GestureSensorManager(object : GestureSensorEventCallback {
        override fun onGestureUpToDown() {
            Log.i(TAG, "onGestureUpToDown")
        }

        override fun onGestureDownToUp() {
            Log.i(TAG, "onGestureDownToUp")
        }

        override fun onGestureRightToLeft() {
            Log.i(TAG, "onGestureRightToLeft")
        }

        override fun onGestureLeftToRight() {
            Log.i(TAG, "onGestureLeftToRight")
        }
    })

fun start() {
    gestureSensorManager.startTracking(context)
}

fun stop() {
    gestureSensorManager.stopTracking()
}
```

## Launcher Extension
### API Document
- [API](./thinklet/sdk/maintenance/dokka/index.md)
### Extension
- THINKLET 標準搭載のLauncherが，`onResume` 実行時に，Launcherの代わりにアプリケーションを起動する機能です．（以降，自動起動モード）
- Launcherから起動するアプリケーションの設定，取得，自動起動モードの無効化，再有効化する機能を提供します．
- 自動起動モードは，端末自体が再起動すると，自動的に有効化されます．起動対象の設定は不揮発として保存されますので，端末自体が再起動しても再設定は不要です．

```
val ext = Extension()
val current = ext.configure() // Pair<String, String> PackageName, className
if (current.first != "com.android.deskclock" || current.second != "com.android.deskclock.DeskClock") {
    ext.configure("com.android.deskclock", "com.android.deskclock.DeskClock")
}

if (!ext.isAutoLaunchMode()) {
    // 再度有効化
    ext.enableAutoLaunchMode()
} else {
    // 無効化
    ext.disableAutoLaunchMode()
}
```

## LED
### API Document
- [API](./thinklet/sdk/led/dokka/index.md)
### LedClient
- カメラと反対，ジェスチャセンサ側の腕部に搭載する緑LEDのON/OFFを切り替える機能を提供します．

```
// ON
LedClient(context).updateCameraLed(true)

// OFF
LedClient(context).updateCameraLed(false)
```

## Power
### API Document
- [API](./thinklet/sdk/maintenance/dokka/index.md)
### Power
- 端末自体をシャットダウン，再起動するための機能を提供します．
- このAPIを介して，シャットダウン，再起動を行うと，`自動起動モード`は，無効化され，自動起動モードの対象に設定したアプリは呼び出しされなくなります．上述した通り，端末自体が再起動すると，自動起動モードは，自動的に有効化されます．

```
Power().shutdown(context, wait = 30000 /* max wait 30s */)
Power().reboot(context, wait = 100 /* soon reboot */)
```

## 開発者モード
### API Document
- [API](./thinklet/sdk/maintenance/dokka/index.md)
### adb
- adbを有効，無効に切り替える機能を提供します．
- **開発時などで，一時的に無効にする際は，再度有効にする手段を先に用意してください．**

```
Log.v(TAG, "adb disable -> enable")
val adbClient = AdbClient(context)
adbClient.disableAsync().thenAccept { disableResult ->
    Log.v(TAG, "adb disable $disableResult")
    adbClient.enableAsync().thenAccept { enableResult ->
        Log.v(TAG, "adb enable $enableResult")
    }
}
```

# プロジェクトへの適応
## Github Packages経由
- 詳細な設定は，[Github 公開されたパッケージの利用](https://docs.github.com/ja/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package) を参照ください．
- また，指定したいVersionを調べる必要や，別途依存ライブラリを追加して頂くお願いがある場合は，`Release` に記載しておりますので，適宜ご確認ください．
- 以下に，サンプルを記載します．
  - root `build.gradle`

    ```
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/FairyDevicesRD/thinklet.app.sdk")
        credentials {
            Properties properties = new Properties()
            properties.load(project.rootProject.file('local.properties').newDataInputStream())
            username = properties.getProperty("username") ?: System.getenv("USERNAME")
            password = properties.getProperty("token") ?: System.getenv("TOKEN")
        }
    }
    ```

  - `app/build.gradle`

    ```
    dependencies {
        implementation 'ai.fd.thinklet:sdk-audio:XXX'
        implementation 'ai.fd.thinklet:sdk-gesture:XXX'
        implementation 'ai.fd.thinklet:sdk-led:XXX'
        implementation 'ai.fd.thinklet:sdk-maintenance:XXX'
    }
    // XXX にはVersionを指定ください．とにかく最新を使用する場合は，`+` に置き換えてください．
    ```

## 更新方法
- implementation で指定するVersionを変更してください．
- その後，プロジェクトをクリーン＆ビルドしてください．

# Tips
## AARのVersionの確認
- 各種AARの `BuildConfig.VERSION` を参照してください．
```
var msg =
    "${ai.fd.thinklet.sdk.audio.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.audio.BuildConfig.VERSION}" + "\n" +
    "${ai.fd.thinklet.sdk.gesture.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.gesture.BuildConfig.VERSION}" + "\n" +
    "${ai.fd.thinklet.sdk.led.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.led.BuildConfig.VERSION}" + "\n" +
    "${ai.fd.thinklet.sdk.maintenance.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.maintenance.BuildConfig.VERSION}"
Log.v(TAG, msg);
```

## プロジェクトをminifyEnabledする場合
- SDKに依存するライブラリまで難読化すると，期待する動作をしなくなります．`proguard-rules.pro` に以下を追記ください．

```.diff
# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
+ -keep class  ai.fd.thinklet.syspropmanager.**{ public *;}
```
