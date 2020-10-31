FROM java:8
VOLUME /tmp
EXPOSE 8082
ADD target/lockedin-0.0.1-SNAPSHOT.jar lockedin-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","lockedin-0.0.1-SNAPSHOT.jar"]