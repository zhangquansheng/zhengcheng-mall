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
3. `springcloud gateway` 基于`Spring Framework 5`、`Project Reactor`、`Spring Boot 2.0`，使用非阻塞式的`API`
   ，内置限流过滤器，支持长连接（比如`websockets`
   ），在高并发和后端服务响应慢的场景下比`zuul`的表现要好
4. `zuul`基于`servlet2.x`构建，使用阻塞的`API`，没有内置限流过滤器，不支持长连接

## 概念解释

- `Route`（路由）：路由是网关的基本单元，由`ID`、`URI`、一组`Predicate`、一组`Filter`组成，根据`Predicate`进行匹配转发。
- `Predicate`（谓语、断言）：路由转发的判断条件，目前`SpringCloud Gateway`支持多种方式，常见如：`Path`、`Query`、`Method`、`Header`等。
- `Filter`（过滤器）：过滤器是路由转发请求时所经过的过滤逻辑，可用于修改请求、响应内容。

## Spring Cloud Gateway 结合 Nacos 实现微服务动态路由

```properties
spring.cloud.gateway.routes[0].id=zhengcheng-mall-api
spring.cloud.gateway.routes[0].uri=lb://zhengcheng-mall-api
spring.cloud.gateway.routes[0].predicates[0]=Path=/mall-api/**
spring.cloud.gateway.routes[0].filters[0]=SwaggerHeaderFilter
spring.cloud.gateway.routes[0].filters[1]=StripPrefix=1
spring.cloud.gateway.routes[1].id=zhengcheng-mall-admin
spring.cloud.gateway.routes[1].uri=lb://zhengcheng-mall-admin
spring.cloud.gateway.routes[1].predicates[0]=Path=/mall-admin/**
spring.cloud.gateway.routes[1].filters[0]=SwaggerHeaderFilter
spring.cloud.gateway.routes[1].filters[1]=StripPrefix=1
spring.cloud.gateway.routes[2].id=zhengcheng-mall-spider
spring.cloud.gateway.routes[2].uri=lb://zhengcheng-mall-spider
spring.cloud.gateway.routes[2].predicates[0]=Path=/mall-spider/**
spring.cloud.gateway.routes[2].filters[0]=SwaggerHeaderFilter
spring.cloud.gateway.routes[2].filters[1]=StripPrefix=1
```

## 限流

## 微服务 - 网关统一鉴权

[Sa-Token](https://sa-token.dev33.cn/doc/index.html#/micro/gateway-auth)

## knife4j Api文档

### afterScript

`/oauth/token` 获取`token`后，设置全局`Header`参数`satoken`
请求参数:

```json
{
  "username": "root",
  "enPassword": "m20ukfuyjQw+MN33ZusmpOzjDq59U5nW4OvdtXU4kW7V7ooXgz057JAox0CYEF4Pd++BEifRTcnIPvgIucTSxSnuqtCocs5fnFcovNsz40YDZNF7YNVSOgYlc+ixRaA9Ff+eRUXUQf32vyeF27jkJmhUTvevF185FdfQZvlNIP0="
}
```

`AfterScript` 如下：

```javascript
var code = ke.response.data.code;
if (code == 200) {
    //获取token
    var token = ke.response.data.data.tokenValue;
    //1、如何参数是Header，则设置当前逻辑分组下的全局Header
    ke.global.setHeader("satoken", token);
}
```