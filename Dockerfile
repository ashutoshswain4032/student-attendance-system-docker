FROM openjdk:19
MAINTAINER "Ashutosh"
COPY target/student-attendance-system-docker-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","student-attendance-system-docker-0.0.1-SNAPSHOT.jar"]