FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/login.jar login.jar
ENTRYPOINT ["java","-jar","/login.jar"]