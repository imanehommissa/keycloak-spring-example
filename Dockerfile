FROM openjdk:8-alpine
COPY /target/example-0.0.1-SNAPSHOT.jar key-value.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","key-value.jar"]