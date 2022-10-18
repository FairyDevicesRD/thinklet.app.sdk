# THINKLET App SDK サンプル

## Prepare
1. `local.properties.sample` を `local.properties` としてコピー
2. `local.properties` を編集

    ```
    # ビルド環境に合わせて設定ください
    sdk.dir=/path/to/android/sdk
    
    # 変更は不要です
    owner=FairyDevicesRD
    repository=thinklet.app.sdk

    # Github Packagesを用いて，ライブラリをLocalへ取得します．下記を参考に発行ください．
    # https://docs.github.com/ja/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token
    # read:packages の権限が必須です
    token=<github token>
    username=<github username>
    ```

## Build

```
# 通常ビルド
./gradlew assembleRelease

# 依存ライブラリを再取得したいとき．`Prepare`で設定した認証情報を更新したときに，正しいかどうかを検証するときに便利です．
./gradlew assembleRelease --refresh-dependencies
```

## How to Use
- THINKLETをAdb接続が利用可能なPCにUSB接続し，[scrcpy](https://github.com/Genymobile/scrcpy)や，[Vysor](https://www.vysor.io/) を用いて，THINKLETの仮想画面をPC上に表示してください．
- 上記でビルドしたものをインストール（Apkへの署名等は，別途行ってください）してください．
1. LauncherExtension
- 画面上の `LauncherExtension` 項目の CLOCKボタン を押すとClockアプリが，自動起動されるように設定されます．ボタンを押したときに合わせて，表示されるダイアログの `DISABLE`を押すと，一時的に自動起動が無効化されます．`ENABLE` を選択すると，自動起動が有効化されます．
- 同様に，THIS APPボタンは本アプリを対象にしたものとなります．
2. Camera角度
- 画面上の `Camera` 項目のプルダウンから，角度を選択し，UPDATE ANGLE を選択することで，ソフトウェア上のカメラ角度を変更します．
3. Gesture
- ジェスチャセンサに対して，手を上下左右に動かすと最後に取得されたイベントを表示します．「↑ ↓ ← →」で表記されます．
4. Power
- 画面上の `Power` 項目のShutdown, Rebootボタンを押すと，電源OFF，再起動を実行します．
5. 録音
- ジェスチャセンサがあるアームのジェスチャセンサ最も近いボタンを押すと，録音を開始します．停止するには，中央のボタン（後述する開発者モード切替）を挟んだボタンを押すと停止します．
- 録音した結果は，`/sdcard/Android/data/ai.fd.thinklet.sample.using.sdk/files/6ch_48kHz_<録音開始の時刻>.raw` に保存されます．
6. LED
- 上述した録音中にLEDが点灯します．
- 録音を停止すると，LEDも消灯します．
7. 開発者モード
- ジェスチャセンサがあるアームの中央のボタンを押すことで，約１秒間AdbをOFFにします．その後，AdbをONにします．

# License
- 本サンプルソースコードはMITライセンスに従います