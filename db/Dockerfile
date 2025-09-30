# Use official OpenJDK 19 image as base
FROM openjdk:19

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY ./target/DevOpsLab01-0.1.0.2-jar-with-dependencies.jar ./app.jar

# Set environment variables for JDBC (optional, can rely on docker-compose)
ENV DB_HOST=db
ENV DB_PORT=3306
ENV DB_NAME=devopsdb
ENV DB_USER=root
ENV DB_PASSWORD=root

# Command to run the Java application
CMD ["java", "-jar", "app.jar"]
