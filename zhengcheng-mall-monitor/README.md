# 基于 Spring Boot Admin 的微服务监控中心

## 简介

Spring Boot 有一个非常好用的监控和管理的源软件，这个软件就是 Spring Boot Admin。该软件能够将 Actuator 中的信息进行界面化的展示，也可以监控所有 Spring Boot
应用的健康状况，提供实时警报功能。

主要的功能点有：

- 显示应用程序的监控状态
- 应用程序上下线监控
- 查看 JVM，
- 线程信息
- 可视化的查看日志以及下载日志文件
- 动态切换日志级别
- Http 请求信息跟踪
- 其他功能点……

可点击 https://github.com/codecentric/spring-boot-admin 更多了解 Spring Boot Admin。

## Spring Boot 应用监控 Actuator 使用的安全隐患

### 安全漏洞

访问`http://xxxxxx/actuator/env`或者`http://xxxxxx/actuator/trace`等地址，泄漏了内部机器ip、端口、访问路径、系统环境变量的配置等信息.

### Springboot2.6.3不兼容swagger3.0.0问题

```java
// https://github.com/springfox/springfox/issues/3462
@Bean
public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
        ServletEndpointsSupplier servletEndpointsSupplier,
        ControllerEndpointsSupplier controllerEndpointsSupplier,
        EndpointMediaTypes endpointMediaTypes,
        CorsEndpointProperties corsProperties,
        WebEndpointProperties webEndpointProperties,
        Environment environment){
        // 暴露了所有端点，不安全
        List<ExposableEndpoint<?>>allEndpoints=new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints=webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath=webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping=new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping=this.shouldRegisterLinksMapping(webEndpointProperties,environment,
        basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping,webEndpoints,endpointMediaTypes,
        corsProperties.toCorsConfiguration(),new EndpointLinksResolver(allEndpoints,basePath),
        shouldRegisterLinksMapping,null);
        }

private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,Environment environment,
        String basePath){
        return webEndpointProperties.getDiscovery().isEnabled()&&(StringUtils.hasText(basePath)
        ||ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
        }
```

## 参考文档

- [actuator-api](https://docs.spring.io/spring-boot/docs/2.6.3/actuator-api/htmlsingle/)
- [Production-ready Features](https://docs.spring.io/spring-boot/docs/2.6.3/reference/html/actuator.html#actuator)