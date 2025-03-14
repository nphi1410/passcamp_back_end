# Stage 1: Build the JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only pom.xml first (this helps with Docker caching)
COPY pom.xml .

# Download dependencies to cache them in a separate layer
RUN mvn dependency:go-offline

# Now copy the entire project (including src/)
COPY . .

# Package the application
RUN mvn clean package -DskipTests

# Debug: Check if the JAR is built
RUN ls -la /app/target

# Stage 2: Run the Application
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
