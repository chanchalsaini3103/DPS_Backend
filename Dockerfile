# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set app directory inside container
WORKDIR /app

# Copy the jar file
COPY target/*.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
