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

    # GitHub Packages経由でライブラリを取得します．下記を参考にアクセストークンを発行ください．
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

## How to use
- THINKLETをAdb接続が利用可能なPCにUSB接続し，[scrcpy](https://github.com/Genymobile/scrcpy)や，[Vysor](https://www.vysor.io/) を用いて，THINKLETの仮想画面をPC上に表示してください．
- 上記でビルドしたものをインストール（APKへの署名等は，別途行ってください）してください．

1. LauncherExtension
   - 画面上の `LauncherExtension` 項目の CLOCKボタン を押すと，Clockアプリが自動起動するように設定されます．ボタンを押したあとに表示されるダイアログの `DISABLE`を押すと，一時的に自動起動が無効化されます．`ENABLE` を選択すると，自動起動が有効化されます．
   - 同様に，THIS APPボタンは本アプリを対象にしたものです．
2. Camera角度
   - 画面上の `Camera` 項目のプルダウンから角度を選択し，`UPDATE ANGLE` を選択すると，ソフトウェア上のカメラ角度を変更します．
   - または，`PORTRAIT` で縦向き，`LANDSCAPE` で横向きにカメラ角度を設定します．
3. Gesture
   - ジェスチャセンサに対して，手を上下左右に動かすと最後に取得されたイベントを表示します．「↑ ↓ ← →」で表記されます．
4. WearingState
   - 近接センサに対して，近づけたり，離したりすることで， `onRemoved`, `onMounted` を表示が切り替わります．
   - 端末を上下に揺すると，`onUpDownBlurred` というトーストが表示されます．
5. Power
   - 画面上の `Power` 項目の`Shutdown`, `Reboot`ボタンを押すと，電源OFF，再起動を実行します．
6. 録音
   - アーム上の3つのボタンのうち，最もアーム先端側のボタンを押すと録音を開始します．アーム先端から最も遠いボタンを押すと停止します．
   - 録音した音声データは，`/sdcard/Android/data/ai.fd.thinklet.sample.using.sdk/files/6ch_48kHz_<録音開始の時刻>.raw` に保存されます．
7. LED
   - 上述した録音中にLEDが点灯します．
   - 録音を停止するとLEDも消灯します．
8. 開発者モード
   - アーム上の中央のボタンを押すことで，約1秒間 adb（[Android Debug Bridge](https://developer.android.com/studio/command-line/adb)）をOFFにします．その後，adbが自動的にONになります．

# License
- 本サンプルソースコードはMITライセンスに従います