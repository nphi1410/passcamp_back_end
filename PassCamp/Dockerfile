# Use Maven as the build image
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy only the pom.xml first (improves caching)
COPY PassCamp/pom.xml ./  

# Download dependencies (useful for caching layers)
RUN mvn dependency:go-offline

# Now copy the full source code
COPY PassCamp ./PassCamp

# Move into the actual project directory
WORKDIR /app/PassCamp

# Build the application
RUN mvn clean package -DskipTests

# Use a smaller runtime image for final execution
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR file from the previous build stage
COPY --from=build /app/PassCamp/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
