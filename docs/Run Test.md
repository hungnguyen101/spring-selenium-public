## I. Run local tests
1. Add a new configuration with environment variables: `spring.profiles.active=stg`. Spring will run the STG profile `application-stg.properties`. 
2. Click the Run button


## II. Run test with Remote WebDriver (Selenium Grid server)
1. Start selenium grid server by using the Docker command below.

```shell
docker-compose up
```


2. Add a new configuration with environment variables: 
   
```shell
spring.profiles.active=browserstack;
BROWSERSTACK_USERNAME=nguynthhng_Ykg5qy;
BROWSERSTACK_ACCESS_KEY=dUXKynsbrzLz18e6SoMP;
```
3. You can combine run stg environment in remote Grid server by using `spring.profiles.active=grid,stg`
3. Click the Run button
4. If you are running your tests in a remote server or StackBrowser, change `spring.profiles.active=grid,stg`

## III. Run test in Jenkins using Maven command
1. Run test in local machine
```shell
mvn clean test -Dspring.profiles.active=qa -Dbrowser=chrome
```
2. Run test in BrowserStack
```shell
 mvn clean test -Dspring.profiles.active=stg,browserstack -Dbrowser=chrome -DBROWSERSTACK_ACCESS_KEY=dUXKynsbrzLz18e6SoMP -DBROWSERSTACK_USERNAME=nguynthhng_Ykg5qy
```

- Each environment starts with a `-D`
- Then Spring will scan those environments in the properties profile