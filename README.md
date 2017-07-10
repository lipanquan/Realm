Realm
第一步项目的build.gradle中增加：
  dependencies {
    classpath "io.realm:realm-gradle-plugin:3.1.1"

第二步Module的build.gradle中增加：
  apply plugin: 'realm-android'

第三步使用：
  Realm.init(this); // 获取Realm实例之前调用
  Realm realm = Realm.getDefaultInstance(); // 获取Realm实例 

使用Realm实例增删改查...
