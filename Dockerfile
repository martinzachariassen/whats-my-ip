# This Dockerfile builds a Java application using Maven and runs it with Eclipse Temurin JRE.
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a multi-stage build to create a smaller final image
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]