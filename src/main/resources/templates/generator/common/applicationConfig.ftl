# 服务端口
server.port=8080

# slf4j配置输出mybatis-dao相关的操作为DEBUG级别, 数据库日志会打印到debug文件中
logging.level.${configInfo.packageName}.dao=DEBUG
logging.config=classpath:logback-spring.xml
logging.file.name=/log/${configInfo.projectName}

# mybaits配置文件
mybatis.mapper-locations = classpath:mapper/*Mapper.xml
mybatis.type-aliases-package = ${configInfo.packageName}.entity

# mysql配置
spring.datasource.driverClassName=${configInfo.driver}
spring.datasource.url=${configInfo.url}
spring.datasource.username=${configInfo.userName}
spring.datasource.password=${configInfo.passWord}