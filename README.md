# README
## bpmsとは
- Batting Performance Management System
- 野球における打撃成績を管理するシステムである

## 起動方法
- 前提
  - AWSのEC2インスタンスへ、自宅等のインターネット環境からSSH接続が出来る状態であること
- 手順の目次
  1. javaのインストール
  2. tomcatのインストール
  3. RDSへの初期データ準備
  4. XXX

1.javaのインストール
 - javaをインストールする
``` 
sudo yum install java-1.8.0-openjdk
```
 - インストール後、バージョン確認を実行
```
java -version
```
手順のとおりであれば、1.8.0がインストールされている

2.tomcatのインストール
 - tomcat をインストールする
```
sudo yum install tomcat
```
 - インストール後、バージョン確認を実行
```
tomcat version
```
2021/01/23現在、手順のとおりであればTomcatは8.5がインストールされている
 - 確認のため、適当なhtmlファイルをROOT配下に配置する
```bash
# ROOT作成
dir="/usr/share/tomcat/webapps/ROOT"
sudo mkdir ${dir}

# index.htmlをコピー
sudo cp ./index.html ${dir}

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

3.RDSへの初期データ準備
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

