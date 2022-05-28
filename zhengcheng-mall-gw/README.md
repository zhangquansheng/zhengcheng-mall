# Spring Cloud Gateway 网关

> [官方文档](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-starter)

## 微服务中网关的作用

- **统一入口**：为全部微服务提供一个唯一的入口，通过网关统一暴露服务，网关起到外部和内部隔离的作用，保障了后台服务的安全性
- **拦截鉴权**：可以提供统一的权限控制，限流等公共服务
- **动态路由**：动态的将请求路由到不同的后端集群中

![springcloud](/img/springcloud.png)

## Gateway 与 Zuul 比较

1. `gateway`是`springcloud`微服务平台的一个子项目，属于`spring`开源社区，依赖名叫：`spring-cloud-starter-gateway`
2. `zuul`是`Netflix`公司的开源项目，`springcloud`在`netflix`项目中也已经集成了`zuul`，依赖名叫：`spring-cloud-starter-netflix-zuul`
   。[官网文档](https://github.com/Netflix/zuul)
3. `springcloud gateway` 基于`spring 5`、`projec` `treactor`、`springboot 2`，使用非阻塞式的`API`，内置限流过滤器，支持长连接（比如`websockets`
   ），在高并发和后端服务响应慢的场景下比`zuul`的表现要好
4. `zuul`基于`servlet2.x`构建，使用阻塞的`API`，没有内置限流过滤器，不支持长连接
