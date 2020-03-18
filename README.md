# keycloak-springboot

This project is showing how we can integrate springboot application with Keycloak Server. Operations like creating user in kecyloak , getting access token using credentials or refreshtoken, authenticating http api request, , logout user.

# Components Version

* _Docker_

* _JAVA_

* _KEYCLOAK IMAGE:7.0.0_

* _MYSQL IMAGE:5.7_

* _MAVEN_

## Pre-requisities

_KEYCLOAK AND MYSQL UP AND RUNNING_ .

_MAVEN CLEAN COMPILE RUN_ 

## Change in application.properties

* keycloak.auth-server-url=http://IMAGENAME:PORT/auth

* keycloak.realm=REALM NAME

* keycloak.resource=CLIENT NAME

* keycloak.credentials.secret=CLIENT SECRET

* spring.datasource.url= MYSQL IMAGE NAME AND PORT AND URL

* server.port=SERVER PORT

_DONT FORGET TO BUILD YOUR JAVA IMAGE FROM THE JAR YOU CREATE_ .

_BUILD KEYCLOAK AND MYSQL IMAGES TOO_

IMPORTANT
__YOU HAVE TO ADD SERVER DATABASE AND TABLE TO MYSQL CONTAINER THEN IT WILL BE SAVED IN THE VOLUME__ ..

TO DO SO :

1. DOCKER-COMPOSE UP -D

1. DOCKER PS

1. DOCKER EXEC -IT MYSQLCONTAINERID SH

1. MYSQL -U root -p

1. Create Database imdb;

1. Create table movies (movie_id int primary key auto_increment, movie_name varchar(100), fav_actor varchar(100))

1. create realm with client , roles and users or just import it from the files in repo its "realm-export" .

## Running application

_DOCKER-COMPOSE UP_

## Test Application

1. Create user by calling /user/create or create it from keycloak page

![](/images/create.png)

2. Get access Token by calling user/token

![](/images/create.png)

3. test token u got be careful it has expire time

![](/images/hello.png)

FACEBOOK :

https://www.facebook.com/profile.php?id=100004377811017

GMAIL :

mohamdhaji0007@gmail.com











