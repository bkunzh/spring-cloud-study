### 打包，跳过测试
mvn clean package -Dmaven.test.skip=true

### 运行jar
java -jar cloud-config-server-1.0-SNAPSHOT.jar --server.port=8081