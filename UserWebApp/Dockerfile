FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/user.jar user.jar
ENTRYPOINT ["java","-jar","/user.jar"]