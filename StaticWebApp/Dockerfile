FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/static.jar static.jar
ENTRYPOINT ["java","-jar","/static.jar"]