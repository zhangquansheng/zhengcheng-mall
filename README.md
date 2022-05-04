# Spring Cloud 大型线上商城项目实战

## 技术选型

- [基于 Layui 的后台管理系统模板](https://gitee.com/pear-admin/Pear-Admin-Layui)

## Jasypt

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
    - [ ] 用户管理
    - [ ] 角色管理
    - [x] 权限管理
    - [x] 行为日志  [通用操作日志组件](https://github.com/mouzt/mzt-biz-log)
    - [ ] 数据字典
2. 公众号管理
    - [x] 用户标签
3. 登录注册 [Sa-Token 权限认证](http://sa-token.dev33.cn/)

