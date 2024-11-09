FROM openjdk:19
ADD target/employees-0.0.1-SNAPSHOT.jar employees-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","employees-0.0.1-snapshot.jar"]