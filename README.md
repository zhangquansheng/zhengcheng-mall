# Spring Cloud 大型线上商城项目实战

## 技术选型

### 系统架构图

> 系统架构图，使用[Processon](https://www.processon.com/) 在线绘制

### 后端技术

|      技术      |           说明            |                             官网                             |
| :------------: | :-----------------------: | :----------------------------------------------------------: |
|  SpringBoot    |          MVC框架          | [ https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
|  SpringCloud   |        微服务框架         |           https://spring.io/projects/spring-cloud/           |
|  MyBatis-Plus  |          ORM框架          |                   https://mp.baomidou.com/                   |
|  Nacos         |        服务发现&配置管理  |                   https://nacos.io/zh-cn/                   |
|  knife4j       |       knife4j是为Java MVC框架集成Swagger生成Api文档的增强解决方案        | [https://gitee.com/xiaoym/knife4j](https://gitee.com/xiaoym/knife4j) |
|  Kibana        |     分析和可视化平台      |               https://www.elastic.co/cn/kibana               |
| Elasticsearch  |         搜索引擎          | [ https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch) |
|     Redis      |        分布式缓存         |                      https://redis.io/                       |
|     Docker     |        容器化部署         |      [ https://www.docker.com](https://www.docker.com/)      |
|     SLF4J      |         日志框架          |                    http://www.slf4j.org/                     |
|     Lombok     |     简化对象封装工具      | [ https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |
|    JustAuth    |     第三方登录的工具      |             https://github.com/justauth/JustAuth             |
|     Hutool     |      Java工具包类库       |                  https://hutool.cn/docs/#/                   |
| Docker Compose |      Docker容器编排       |               https://docs.docker.com/compose/               |
|   Portainer    |     Docker可视化管理      |            https://github.com/portainer/portainer            |
|   Leaf         |   美团点评分布式ID生成系统      |            https://tech.meituan.com/2017/04/21/mt-leaf.html            |
|   JustAuth     |   开箱即用的整合第三方登录的开源组件      |            https://justauth.wiki/           |
|   IJPay        |   让支付触手可及      |            https://gitee.com/javen205/IJPay           |
|   Kaptcha      |   基于 SpringBoot 和 Google Kaptcha 的简单验证码组件，简单验证码就选它      |            https://gitee.com/baomidou/kaptcha-spring-boot-starter           |
|   Lock4j       |   基于 SpringBoot 同时支持 RedisTemplate、Redission、Zookeeper 的分布式锁组件。      |            https://gitee.com/baomidou/lock4j           |
|   DDD       |   This is the new home of the original DDD Sample app hosted at SourceForge.      |            https://github.com/citerus/dddsample-core           |
|   Spring Boot Admin       |     Spring Boot Admin 用于监控基于 Spring Boot 的应用，它是在 Spring Boot Actuator 的基础上提供简洁的可视化 WEB UI。  |            https://codecentric.github.io/spring-boot-admin           |
|   Seata                  | 全局事务管理框架     | https://github.com/seata/seata                       |
|   Portainer              | 可视化Docker容器管理 | https://github.com/portainer/portainer               |
|   Jenkins                | 自动化部署工具       | https://github.com/jenkinsci/jenkins                 |
|   Kubernetes             | 应用容器管理平台     | https://kubernetes.io/
|   dynamic-tp             | 基于配置中心的轻量级动态线程池     | https://dynamictp.cn/
|   mzt-biz-log            | Springboot-注解-通用操作日志组件     | https://github.com/mouzt/mzt-biz-log
|   WxJava             | 微信开发 Java SDK，支持微信支付、开放平台、公众号、企业号/企业微信、小程序等的后端开发     | https://gitee.com/binary/weixin-java-tools

### 前端技术

|         技术          |                  说明                   |                             官网                             |
| :-------------------: | :-------------------------------------: | :----------------------------------------------------------: |
|        layui         |                前端框架                 |                     https://www.layui.site/                    |
|      Pear Admin Layui       |       Pear Admin 是 一 款 开 箱 即 用 的 前 端 开 发 模 板                 |                  https://gitee.com/pear-admin/Pear-Admin-Layui                   |
|      xm-select       |       layui 的一个多选解决方案                 |                  https://maplemei.gitee.io/xm-select                   |
|      layui-soul-table       |       给 layui-table 注入点灵魂                |                 https://gitee.com/saodiyang/layui-soul-table                   |

## 快速启动

### Jasypt

增加启动参数：

```
-Djasypt.encryptor.password=123456
```

## zhengcheng-mall-im 即时聊天

即时聊天基于`socket.io`，使用`netty-socketio`

### 配置

```properties
rt-server.host=localhost
rt-server.port=9092
rt-server.ping-interval=300000
rt-server.upgrade-timeout=25000
rt-server.ping-timeout=60000
rt-server.token=123456
rt-server.redisson.enable=true 
rt-server.redisson.host=127.0.0.1
rt-server.redisson.port=6379
rt-server.redisson.password=123456
```

## 内置功能

1. 系统管理
    - [x] 用户管理
    - [x] 角色管理
    - [x] 权限管理
    - [x] 行为日志  [通用操作日志组件](https://github.com/mouzt/mzt-biz-log)
    - [x] 数据字典
2. 公众号管理
    - [x] 用户标签
3. 登录注册 [Sa-Token 权限认证](http://sa-token.dev33.cn/)
4. 商品管理
    - [x] SKU管理
    - [x] 商品分类
5. 支付服务
6. IM服务
