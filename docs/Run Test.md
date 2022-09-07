## I. Run local tests
1. Add a new configuration with environment variables: `spring.profiles.active=stg`. Spring will run the STG profile `application-stg.properties`. 
2. Click the Run button


## II. Run test with Remote WebDriver (Selenium Grid server)
1. Start selenium grid server by using the Docker command:
```shell
docker-compose up
```
2. Add a new configuration with environment variables: `spring.profiles.active=remote`. Spring will run the Remote profile `application-remote.properties`.
3. You can combine run stg environment in remote Grid server by using `spring.profiles.active=remote,stg`
3. Click the Run button
4. If you are running your tests in a remote server or StackBrowser, change `localhost` to your `server IP address`
```shell
selenium.grid.url=http://localhost:4444/wd/hub
```
## III. Run test in Jenkins using Maven command

```shell
mvn clean test -Dspring.profiles.active=qa -Dbrowser=chrome
```
- Each environment starts with a `-D`
- Then Spring will scan those environments in the properties profile