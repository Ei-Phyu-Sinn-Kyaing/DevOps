# Use OpenJDK 19 to match your compiled project
FROM openjdk:19
COPY ./target/DevOpsLab01-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOpsLab01-0.1.0.2-jar-with-dependencies.jar"]
