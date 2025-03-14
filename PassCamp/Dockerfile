# Use official Java runtime
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file
COPY target/*.jar app.jar

# Expose port 8080 (or change if needed)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
 
