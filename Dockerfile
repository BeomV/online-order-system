# Use an official OpenJDK image as a base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the build/libs directory to the container
COPY build/libs/online-order-system-0.0.1-SNAPSHOT.jar app.jar
# Run the jar file

CMD ["java", "-jar", "app.jar"]
