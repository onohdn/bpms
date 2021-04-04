# README
## bpmsとは
- Batting Performance Management System
- 野球における打撃成績を管理するシステムである

## 起動方法
- 前提
  - AWSのEC2インスタンスへ、自宅等のインターネット環境からSSH接続が出来る状態であること
  - mavenによる資材作成ができる状態であること
- 手順の目次
  1. javaのインストール
  2. tomcatのインストール
  3. tomcat設定ファイルの修正
  4. RDSへの初期データ準備
  5. AP資材の準備
  6. AP資材のデプロイ

## 1.javaのインストール
 - javaをインストールする
```bash
sudo yum install java-1.8.0-openjdk
```
 - インストール後、バージョン確認を実行
```bash
java -version
```
手順のとおりであれば、1.8.0がインストールされている

## 2.tomcatのインストール
 - tomcat実行ユーザの作成
```bash
useradd -s /sbin/nologin tomcat
```
 - tomcat資材のダウロード
   - yumリポジトリは使用しない。バージョン固定のため。
```bash
wget https://ftp.kddi-research.jp/infosystems/apache/tomcat/tomcat-8/v8.5.64/bin/apache-tomcat-8.5.64.tar.gz
```
 - tomcat をインストールする
```bash
tar -xzvf ./apache-tomcat-8.5.64.tar.gz
mv ./apache-tomcat-8.5.64 /opt
cheown -R tomcat:tomcat /opt/apache-tomcat-8.5.64
```
 - tomcatサービスの登録
   - root権限で、Unit作成する
     - /usr/lib/systemd.system/tomcat.service を作成し、中身は以下を記載する
```
# Systemd unit file for default tomcat
#
# To create clones of this service:
# DO NOTHING, use tomcat@.service instead.

[Unit]
Description=Apache Tomcat Web Application Container
After=syslog.target network.target

[Service]
Type=oneshot
PIDFile=/opt/apache-tomcat/tomcat.pid
RemainAfterExit=yes
#EnvironmentFile=/etc/tomcat/tomcat.conf
#Environment="NAME="
#EnvironmentFile=-/etc/sysconfig/tomcat
ExecStart=/opt/apache-tomcat/bin/startup.sh
ExecStop=/opt/apache-tomcat/bin/shutdown.sh
ExecReStart=/opt/apache-tomcat/bin/shutdown.sh;/opt/apache-tomcat/bin/startup.sh
SuccessExitStatus=143
User=tomcat
Group=tomcat

[Install]
WantedBy=multi-user.target

```
 - tomcat自動起動設定
```bash
systemctl enable tomcat.service
```
 - バージョン確認
```bash
/opt/apache-tomcat/bin/version.sh
```
手順のとおりであればTomcatは8.5.64がインストールされている
 - 確認のため、適当なhtmlファイルをROOT配下に配置する
   - htmlファイルの配置をしない場合、例のtomcatページが開くので、htmlファイル配置は任意。
```bash
# ROOT作成
dir="/usr/share/tomcat/webapps/ROOT"
sudo mkdir ${dir}

# index.htmlをコピー
sudo cp ./test/index.html ${dir}

# 所有を変更
sudo chown -R tomcat:tomcat ${dir}
```
 - tomcatサービスを起動
```bash
# 起動
sudo systemctl start tomcat
# 確認
systemctl status tomcat # active (running)となっていること
```
 - 以下にインターネットブラウザでアクセスする
```
http://EC2のパブリックIP:8080
```
先程配置したhtmlファイルが表示されればOK
 - html配置を行わない場合、例のtomcatページが開けばOK

## 3.tomcatの事前設定
 - server.xmlを修正する
```xml
<!- Hostタグ内、autoDeployにfalseを設定する -->
<Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="false">
<!- 以下省略 -->
```
 - bpms環境設定ファイルの追加
   - bpms-envモジュール内にある `context.xml` を `bpms-web.xml` という名前で配置する
     - `context.xml` の在り処：`bpms-env/configs/production-server/ContainerConfigXML` 
     - 配置先：`/opt/apache-tomcat/conf/Catalina/localhost/`

## 4.RDSへの初期データ準備
 - RDS（postgresql）に接続する
 - DBを作成する
```
create database bpmsdb;
```
 - ユーザーを作成する
```
create role bpmsuser with password `何らか任意のパスワード` login;

grant rds_superuser to bpmsuser;
```
 - DBの権限を変更する
```
ALTER DATABASE bpmsdb OWNER TO bpmsuser;
```
 - DBとユーザーを切り替える
```
\c bpmsdb

\connect - bpmsuser
```
 - 別紙 `SetUpSQL.txt`に従って、SQLを実行する

## 5.AP資材の準備
 - この項目はローカルPCでの作業である
 - プロジェクトのルートディレクトリに移動する
```bash
#ディレクトパスは例です。自身でチェックアウトしたパスと読み替えてください。
cd C:\work\bpms
```
 - warファイル作成
```bash
#bpms-envモジュールを除いたwarを作成する
mvn -P warpack clean install

#bpms-web\target\bpms-web.warが出来上がっているはずである
```
 - bpms-envモジュールのディレクトリに移動する
```bash
#ディレクトパスは例です。自身でチェックアウトしたパスと読み替えてください。
cd C:\work\bpms\bpms-env
```
 - 本番環境用のjarファイル作成
```bash
#bpms-envモジュールの本番環境用jarを作成する
mvn -P production-server clean package

#bpms-env\target\bpms-env-1.0.0-SNAPSHOT-production-server.jarが出来上がっているはずである
```
## 6.AP資材のデプロイ
 - 前項目で作成したwarとjarを、EC2上へ転送する
 - warを以下のパスに転送し、展開する
   - /opt/apache-tomcat-8.5.64/webapps/
 - jarを以下のパスに転送する
   - /etc/libs

## 7.動作確認
 - tomcatを起動（既に起動状態の場合は、停止→起動する）
```bash
#起動コマンド
systemctl start tomcat.service
#停止コマンド
systemctl stop tomcat.service
```
 - 以下にインターネットブラウザでアクセスする
```
http://EC2のパブリックIP:8080/bpms-web/
```
 - bpmsのログイン画面が表示されればOK
   - ログイン情報は、入力項目にデフォルト入力されている `demo,demo` で認証可能
