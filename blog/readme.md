# 스프링부트 블로그 프로젝트 V2

## 의존성
- spring-boot-starter-web
- spring-boot-devtools
- spring-boot-starter-data-jpa
- spring-boot-starter-oauth2-client
- spring-boot-starter-security
- mysql-connector-java
- lombok


## DB설정

```sql

create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database cos;

```