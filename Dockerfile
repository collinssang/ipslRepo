FROM ubuntu:latest
LABEL authors="Collins Sang"

ENTRYPOINT ["top", "-b"]

# Use a base image with JDK and Maven installed
FROM maven:3.2.2-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project and pom.xml
COPY pom.xml .

# Download dependencies and package the application
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:go-offline dependency:resolve-plugins
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml package

# Copy the application code
COPY src ./src

# Build the application
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml clean package

# Create a new image with the built application
FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/ipsl-task.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]