# Configure log level

# I. Change log level for the entire project, or a specific package
Spring Boot provide logging feature. This will avoid using `IF ELSE `to control the environments.
```shell
//root is the whole project
logging.level.root=warn

//you can specify the log level for a specific package
logging.level.{specific_package}={log_level}
logging.level.org.springframework.web=debug
logging.level.org.hibernate=error
```

# II. Change log level for different environments PRO, STG, UAT
1. Go to the specific profile. For example, `application-browserstack.properties`.
2. Add log level

If you want to log `DEBUG` level in your local environment, set application.properties profile to DEBUG.
If you want to log `WARN` level in your remote environment with Browser Stack, set application-browserstack profile to WARN. 

# III. Write logging into a file
- In profile properties, modify: `logging.file.name={path}`
- Examples:
```shell
logging.file.name=./log/test-execution-local.log
```
  


Refer: https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-logging.html#boot-features-custom-log-levels