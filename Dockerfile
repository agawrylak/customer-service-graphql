# Use a JDK image that includes Gradle
FROM eclipse-temurin:17-jdk-alpine AS builder

# Set a working directory
WORKDIR /app

# Install necessary tools
RUN apk add --no-cache bash curl

# Copy the Gradle wrapper and build files separately to leverage Docker caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Grant execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Download and cache Gradle dependencies
RUN ./gradlew --version

# Copy the source code of your application
COPY src src

# Build the fat JAR using the custom task
RUN ./gradlew shadowJar -x test

# Use Eclipse Temurin JRE for running the application
FROM eclipse-temurin:17-jre-alpine

# Set a working directory in the new stage
WORKDIR /app

# Copy the built fat JAR artifact from the builder stage
COPY --from=builder /app/build/libs/*-standalone.jar app.jar

# Expose the port your application listens on
EXPOSE 8080

# Define environment variables for your application (if needed)
# ENV SOME_VARIABLE value

# Run the application with a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Command to run the application
CMD ["java", "-jar", "app.jar"]