# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only the pom.xml first (improves caching)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the entire project source code
COPY . .

# Build the application (skip tests to speed up build)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
