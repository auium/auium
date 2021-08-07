# Auium
基于WebDriverAgent实现的简单驱动

## 安装
mvn命令
```shell
mvn clean install -DskipTests
```

项目内当mvnw命令
```shell
./mvnw clean install -DskipTests
```

## 使用

- 使用依赖
```xml
<dependency>
    <groupId>com.auium</groupId>
    <artifactId>auium</artifactId>
    <version>0.0.1</version>
</dependency>
```

- example code
```kotlin
val driver = Driver("http://localhost:8100")
driver.session("your app bundleId")
driver.close()
```