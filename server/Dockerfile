#FROM openjdk:9.0.4
FROM maven:3.6.3-openjdk-11
ENV HOME=/tmp
RUN apt-get update && apt-get install -y wait-for-it
ADD pom.xml $HOME
WORKDIR $HOME
RUN ["mvn", "verify", "clean", "--fail-never"]
ADD . $HOME
RUN ["mvn", "package", "-X"]
#RUN ["mvn", "clean", "package", "spring-boot:repackage", "-X"]
EXPOSE 8080
CMD ["wait-for-it", "-s", "postgres:5432", "--", "java", "-jar", "target/server-0.0.1-SNAPSHOT.jar"]
