# 基于Spring Boot Admin的微服务监控中心

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