# msa-demo
微服务架构demo，整合spring mvc，spring oauth2，eureka，zuul

# 启动方式
## 启动eureka服务器
mvn spring-boot:run -f eureka-server/pom.xml -Dspring.profiles.active=p0
mvn spring-boot:run -f eureka-server/pom.xml -Dspring.profiles.active=p1
## 启动Oauth2服务器
mvn spring-boot:run -f oauth2-server/pom.xml
## 启动资源服务
mvn spring-boot:run -f service-product/pom.xml
mvn spring-boot:run -f service-order/pom.xml
## 启动网关
mvn spring-boot:run -f front/pom.xml

# 登录
打开
http://localhost:8080/order/list
将会跳转到oauth2登录页面

打开
http://localhost:8080/product/list
无需登录可以直接查看
