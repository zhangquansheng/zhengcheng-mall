# 基于Spring Boot Admin的微服务监控中心

## Spring Boot 应用监控 Actuator 使用的安全隐患

### 安全漏洞

访问`http://xxxxxx/actuator/env`或者`http://xxxxxx/actuator/trace`等地址，泄漏了内部机器ip、端口、访问路径、系统环境变量的配置等信息