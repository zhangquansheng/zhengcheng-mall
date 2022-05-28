# Spring Cloud Gateway 网关

> [官方文档](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-starter)

## 微服务中网关的作用

- **统一入口**：为全部微服务提供一个唯一的入口，通过网关统一暴露服务，网关起到外部和内部隔离的作用，保障了后台服务的安全性
- **拦截鉴权**：可以提供统一的权限控制，限流等公共服务
- **动态路由**：动态的将请求路由到不同的后端集群中

![springcloud](/img/springcloud.png)