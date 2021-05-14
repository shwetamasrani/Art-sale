FROM openjdk:8
MAINTAINER Kanchan Mahajan kanchanmahajan67@gmail.com
COPY ./target/speart-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
EXPOSE 8099
CMD ["java", "-jar", "speart-0.0.1-SNAPSHOT.jar"]
