# Spring Cloud 大型线上商城项目实战

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

