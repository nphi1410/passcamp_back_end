# Stage 1: Build the JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy entire project (including pom.xml)
COPY . .  # Ensure this includes pom.xml

# Verify pom.xml exists before running Maven
RUN ls -la /app && mvn clean package -DskipTests

# Stage 2: Run the Application
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
