FROM openjdk:8
EXPOSE 8081
ADD target/static.jar static.jar
ENTRYPOINT ["java","-jar","/static.jar"]