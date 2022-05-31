FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY target/FileApp-0.0.1-SNAPSHOT.jar FileApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/FileApp-0.0.1-SNAPSHOT.jar"]

