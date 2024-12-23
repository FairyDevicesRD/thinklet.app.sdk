- [THINKLET App SDK](#thinklet-app-sdk)
- [ご利用にあたって](#ご利用にあたって)
  - [thinklet/sdk (documents)](#thinkletsdk-documents)
  - [sample](#sample)
- [機能一覧](#機能一覧)
  - [Audio](#audio)
    - [API Document](#api-document)
    - [MultiChannelAudioRecord](#multichannelaudiorecord)
    - [RawAudioRecordWrapper](#rawaudiorecordwrapper)
    - [MicGainControl](#micgaincontrol)
    - [VolumeControl](#volumecontrol)
    - [Suppress SystemSound](#suppress-systemsound)
    - [(試験的機能) 音声処理](#試験的機能-音声処理)
  - [Camera](#camera)
    - [API Document](#api-document-1)
    - [Angle](#angle)
  - [CameraX拡張機能](#camerax拡張機能)
    - [CameraX + ThinkletMic](#camerax--thinkletmic)
  - [Gesture](#gesture)
    - [API Document](#api-document-2)
    - [GestureSensorEventCallback](#gesturesensoreventcallback)
    - [GestureSensorManager](#gesturesensormanager)
    - [(試験的機能) WearSensorManager](#試験的機能-wearsensormanager)
  - [Launcher Extension](#launcher-extension)
    - [API Document](#api-document-3)
    - [Extension](#extension)
  - [LED](#led)
    - [API Document](#api-document-4)
    - [LedClient](#ledclient)
  - [Power](#power)
    - [API Document](#api-document-5)
    - [Power](#power-1)
  - [Language](#language)
    - [API Document](#api-document-6)
    - [Language](#language-1)
  - [開発者モード](#開発者モード)
    - [API Document](#api-document-7)
    - [adb](#adb)
- [プロジェクトでの利用方法](#プロジェクトでの利用方法)
  - [GitHub Packages経由](#github-packages経由)
  - [更新方法](#更新方法)
- [Tips](#tips)
  - [AARのバージョンの確認](#aarのバージョンの確認)
  - [プロジェクトを難読化する場合](#プロジェクトを難読化する場合)
    - [minifyEnabled](#minifyenabled)
- [開発ロードマップ](#開発ロードマップ)

# THINKLET App SDK
THINKLET本体にインストールするアプリケーションに使用することができるSDKを提供します．

# ご利用にあたって
- このライブラリ（AARファイル）を許可なく再配布・再利用はしてはいけません．
- 本レポジトリに含まれるドキュメントに明示的に許可されている用途以外の利用を禁止します．
- 当社は、お客様による本ライブラリの利用に関しては保証しません．
- ライブラリおよびこれを利用するサンプルソースコードは、THINKLET実機でのみ動作します．

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
- オプションで，バッファサイズ，チャンネル数の指定，サンプリングレートの指定が可能です．

```.kt
# 6ch(5ch + 1ch(=empty)), SamplingRate 48kHz
val audioRecord = MultiChannelAudioRecord().get(Channel.CHANNEL_SIX, SampleRate.SAMPLING_RATE_48000).audioRecord
```

### RawAudioRecordWrapper
- `AudioRecord`を簡易的に扱うためのクラス．
- 録音結果を出力する `OutputStream`，結果を受け取る `IRawAudioRecorder` を提供します．
- オプションで，参照音ありの6ch，参照音なしの5chで録音，サンプリングレートを指定，出力チャンネル数をモノラル(1ch)，ステレオ(2ch)に変換できます．

```.kt
private val randomFileName: String
    get() = "6ch_48kHz_${SimpleDateFormat("yyyy-MM-dd-hh-mm-ss", Locale.getDefault()).format(Date())}.raw"
private val rawFileOutputStream: FileOutputStream
    get() = FileOutputStream(File(activity.getExternalFilesDir(null), randomFileName))
private val rawRecorder = RawAudioRecordWrapper(
    channel = MultiChannelAudioRecord.Channel.CHANNEL_SIX,
    sampleRate = MultiChannelAudioRecord.SampleRate.SAMPLING_RATE_48000,
    outputChannel =  RawAudioRecordWrapper.RawAudioOutputChannel.ORIGINAL
)
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
    if (!rawRecorder.prepare(context)) {
        return
    }
    rawRecorder.start(rawFileOutputStream, rawRecorderCallback)
}
```

### MicGainControl
- マイクのゲインを調整する機能を提供します．
- ゲインレベルはアプリごとではなく，全てのアプリで共有されます．したがって，他のアプリへ悪影響が出ないように，アプリ終了時には，`resetMicGain()` を呼び出すようにしてください．

```
private val micGainControl = MicGainControl(context)

// increase gain
micGainControl.micGain(18)

// reset gain. (default:12)
micGainControl.resetMicGain()
```

### VolumeControl
- 音量を段階的に変更する機能を提供します．
- 変更するストリームについては，APIドキュメントを参照ください．

> [!TIP]
> この機能はFW 7.000.0以降で，利用可能です．

```
private val volumeCtrl = VolumeControl(context, lifecycle)
volumeCtrl.enable()

// enabled loop: stepUp volume. 0->1->2->3->0->1->...
volumeCtrl.stepUp()
// enabled loop: stepDown volume. 3->2->1->0->3->2->...
volumeCtrl.stepDown()

// disabled loop: stepUp volume. 0->1->2->3->3->3->...
volumeCtrl.stepUp(enableLoop = false)
// disabled loop: stepDown volume. 3->2->1->0->0->0->...
volumeCtrl.stepDown(enableLoop = false)
```

### Suppress SystemSound
- THINKLET固有のシステム音の再生を抑制します．
  - e.g. 「オンラインになりました」を抑えます．

> [!TIP]
> この機能はFW 11.000.0以降で，利用可能です．

```
private val soundCtrl = SystemSound(context)

// Suppress THINKLET's system sound
soundCtrl.muteMild()
// Minimize THINKLET's system sound
soundCtrl.muteMinimum()
// Remove suppress THINKLET system sound
soundCtrl.reset()
```

### (試験的機能) 音声処理
- 弊社の音声処理技術を活用した装着者の音声を強調するようにマイクの設定を行います．

| ![音声強調のイメージ](https://mimi.fairydevices.jp/img/technology/edge/xfe/bf/img_01.png) |
| :---------------------------------------------------------------------------------------: |
|                                    音声強調のイメージ                                     |

> [!NOTE]
> FairyDevicesの音声処理技術については，[こちら](https://mimi.fairydevices.jp/technology/edge/xfe/)をご参照ください．  
> 本機能では，[ビームフォーマ](https://mimi.fairydevices.jp/technology/edge/xfe/bf/) のみ提供し，装着者方向の音声のみを強調，その他の周辺の音声を抑えます．

> [!TIP]
> この機能はFW 11.000.0以降で，利用可能です．

> [!IMPORTANT]
> この機能は，試験的機能です．予期せぬエラー，APIの変更が発生することがあります．  
> また，THINKLET cube モデルはサポートしていません．

```
private val am = context.getSystemService(AudioManager::class.java)
private val xfeCtl = ThinkletXfe()

// enable xfe
if (!xfeCtl.configureXfe(audioManager = am, xfeEnable = true)) {
    return
}
val audioRecord = AudioRecord.Builder()
    .setAudioSource(AudioSource.MIC)
    .setAudioFormat(
        AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(48000) // or 16000
            .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
            .build()
    )
    .setBufferSizeInBytes(1920)
    .build()
// check xfe mode.
if (!audioRecord.canXfeRecord()) {
    audioRecord.release()
    return
}

audioRecord.startRecording()
// do something

audioRecord.stop()
audioRecord.release()
xfeCtl.configureXfe(audioManager = am, xfeEnable = false)
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
if (camAngle.current() != 90) {
    val ret = camAngle.rotate_90()
}
```

- また，角度を意識しない場合は，`setLandscape`, `isLandscape`, `setPortrait`, `isPortrait` をご利用ください．

## CameraX拡張機能
### CameraX + ThinkletMic
- CameraX の [androidx.camera.video](https://developer.android.com/reference/kotlin/androidx/camera/video/package-summary) をフォークし，音声周りをカスタマイズできるようにしました．

> [!NOTE]
> 本機能は，THINKLET App SDKに含みません．サンプルやご利用方法については，[thinklet.camerax.mic](https://github.com/FairyDevicesRD/thinklet.camerax.mic) をご確認ください．

- 通常のCameraXの [Recorder](https://developer.android.com/reference/kotlin/androidx/camera/video/Recorder) では，マイクに関する機能は，CameraX内部に隠蔽されており，開発者が選ぶことはできません．  
この機能により，例えば，THINKLETの５つのマイクをすべて使い，装着者の音声を強調して録音できます．  
また，次のような音を使用するケースも理論上実現可能です．
    - 「人の声を検出しているときの音」（サンプルあり）  
    - 「音声認識し，それを合成音声した音」  
    - 「BGMを追加した音」
- 利用するには，すでにCameraXのRecorderを利用している場合は，ライブラリの導入と，`Recorder.Builder` に `setThinkletMic` を追加します．
  - 5chマイクで録音する
  ```diff
  val recorder = Recorder.Builder()
      .setExecutor(recorderExecutor)
      .setQualitySelector(QualitySelector.from(Quality.FHD))
  +   .setThinkletMic(ThinkletMics.FiveCh)
      .build()
  ```
  - また，[本SDKの音声処理機能](#試験的機能-音声処理) にも対応しています．
  ```diff
  val recorder = Recorder.Builder()
      .setExecutor(recorderExecutor)
      .setQualitySelector(QualitySelector.from(Quality.FHD))
  +   .setThinkletMic(ThinkletMics.Xfe(audioManager))
      .build()
  ```

> [!TIP]
> 一部機能については，一般的なAndroidデバイスでも動作できることを確認していますが，積極的なサポートはしておりません．

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

### (試験的機能) WearSensorManager
- THINKLETの装着状態のセンサーイベントを簡易的に扱うためのクラス．
- 試験的機能となります．利用時には，`@OptIn(ExperimentalFeature::class)` を付与してください．
- `WearSensorManager.IGyroscopeEvent` で，ジャイロセンサーをもとにしたイベントコールバックを提供します．
  - 注意：`onSideBlurred` は，使い方によっては高頻度で通知されます．
- `WearSensorManager.IProximityEvent` で，近接センサーをもとにしたイベントコールバックを提供します．

```
private val wearSensorManager = WearSensorManager(context)
private val proximityEvent = object: WearSensorManager.IProximityEvent {
    override fun onMounted() {
        Log.i(TAG, "onMounted")
    }

    override fun onRemoved() {
        Log.i(TAG, "onRemoved")
    }

}
private val gyroscopeEvent = object: WearSensorManager.IGyroscopeEvent {
    override fun onUpDownBlurred() {
        Log.i(TAG, "onUpDownBlurred")
    }

    override fun onSideBlurred() {
        Log.i(TAG, "onSideBlurred")
    }
}

fun start() {
    wearSensorManager.startTracking(proximityEvent, gyroscopeEvent)
}

fun stop() {
    wearSensorManager.stopTracking()
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

## Language
### API Document
- [API](./thinklet/sdk/maintenance/dokka/index.md)
### Language
- 端末の言語設定を変更する機能を提供します．
- 本機能で，切り替え可能な一覧は，`getAvailableLocales` より取得できます．

```
val lang = Language(context)
lang.updateRequest(Locale.GERMAN)
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

# プロジェクトでの利用方法
## GitHub Packages経由
- 詳細な設定は，[GitHub 公開されたパッケージの利用](https://docs.github.com/ja/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package) を参照ください．
- また，バージョンの指定や依存ライブラリの追加が必要な場合は，`Release` に記載しておりますので，適宜ご確認ください．
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
## AARのバージョンの確認
- 各種AARの `BuildConfig.VERSION` を参照してください．
```
val msg =
    "${ai.fd.thinklet.sdk.audio.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.audio.BuildConfig.VERSION}" + "\n" +
    "${ai.fd.thinklet.sdk.gesture.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.gesture.BuildConfig.VERSION}" + "\n" +
    "${ai.fd.thinklet.sdk.led.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.led.BuildConfig.VERSION}" + "\n" +
    "${ai.fd.thinklet.sdk.maintenance.BuildConfig.LIBRARY_PACKAGE_NAME}: ${ai.fd.thinklet.sdk.maintenance.BuildConfig.VERSION}"
Log.v(TAG, msg);
```

## プロジェクトを難読化する場合
### minifyEnabled
- `minifyEnabled` を用いて，SDKに依存するライブラリまで難読化すると，期待する動作をしなくなります．`proguard-rules.pro` に以下を追記ください．

```.diff
# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
+ -keep class  ai.fd.thinklet.syspropmanager.**{ public *;}
```

# 開発ロードマップ
- 開発中・または開発計画をしている機能です．なお，リリースを保証するものではありません．予告なく中止される可能性があります．

  - ネットワーク(Wi-Fi/APN) CRUD API
    - THINKLETのWi-Fi，APNのCRUD（追加・取得・更新・削除）
  - Bluetooth CRUD API
    - THINKLETと接続するBluetooth機器とのペアリング機能
  - (汎用Android向けライブラリ) ローカルTHINKLET操作API
    - THINKLETがオフラインでも，AndroidスマートフォンからTHINKLETを操作するライブラリ
