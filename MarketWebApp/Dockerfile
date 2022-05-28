FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/market.jar market.jar
ENTRYPOINT ["java","-jar","/market.jar"]