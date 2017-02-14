# config-spring-boot-stater

## 1说明

spring boot的自动配置，根据条件检测是否开启相应的自动配置。

将自动配置抽离出，便于解耦。

## 2自动配置

* 跨域配置。配置一个优先级最高的跨域filter。开启自动配置的条件是满足`cors.enable=true`，下面是配置：
```
cors:
  enable: true
  mapping: /**
  allowedOrigins: http://localhost:9090
  allowedMethods: GET,POST,PUT,DELETE
  allowCredentials: false
  maxAge: 3600
```