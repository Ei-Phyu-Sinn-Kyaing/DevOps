# Use OpenJDK 19 to match your compiled project
FROM openjdk:19
COPY ./target/classes/com /tmp/com
WORKDIR /tmp
ENTRYPOINT ["java", "com.napier.sem.App"]
