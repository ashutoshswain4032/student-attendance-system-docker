FROM openjdk:19
MAINTAINER "Ashutosh"
COPY target/SASD-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","SASD-0.0.1-SNAPSHOT.jar"]
