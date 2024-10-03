# Seek Challenge - Candidates Microservice

This repo contains a microservice developed with Spring Boot 3, MySQL, Flyway, Swagger 3, JUnit and Mockito for testing. It is based on Hexagonal architecture and managed information about candidates.

This project has two certs and they were generated with OpenSSL. It's not necessary generate new certs, but if you want to create others please follow this **[link][openssl]**.

You can test this microservice with the postman collection and envs that you can find **[here][postman]**. 

For local test, you have to clone this repo and use *Seek Challenge - local - env.json* and you will need to set up a MySql database and these environments variables for Mysql connection:

```
MYSQL_HOST=localhost;
MYSQL_PORT=3306;
MYSQL_SCHEME=candidates_db;
MYSQL_USERNAME=candidate-user;
MYSQL_PASSWORD=uno-muy-dificil
```

For test over Heroku, you must use *Seek Challenge - heroku - env.json* and you can review the documentation in **[swagger][swagger]**.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[openssl]: <https://github.com/317h0n/seek-challenge/blob/main/src/main/resources/certs/instructions.md>
[postman]: <https://github.com/317h0n/seek-challenge/tree/main/docs/collections>
[swagger]: <https://echocano-seek-challenge-30e009b05e43.herokuapp.com/swagger-ui/index.html>
