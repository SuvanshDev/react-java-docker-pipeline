# # Multi stage build (Build Stage)
# # Use Maven as the base image for the build stage
# FROM maven:3.9.9-eclipse-temurin-21 AS build
# # Copy the entire project directory into the /app directory inside the container
# COPY . /app
# # Set the working directory to /app inside the container
# WORKDIR /app
# # Run Maven to clean and build the project, producing the JAR file
# RUN mvn clean package
# # Runtime Stage
# FROM eclipse-temurin:21-jre-alpine 
# # Copy the built JAR file from the build stage to the runtime image
# COPY --from=build /app/target/dockermastery-0.0.1-SNAPSHOT.jar /app/dockermastery.jar
# # Set the working directory to /app inside the runtime container
# WORKDIR /app
# #Expose port 8080 for the application to listen on
# EXPOSE 8080
# # Define the entry point to run the application
# ENTRYPOINT ["java", "-jar", "dockermastery.jar"]

# Stage 1: Build the frontend (Node.js)
FROM node:18 AS builder-frontend
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build

# Stage 2: Build the backend (Java) with frontend assets
FROM maven:3.9.9-eclipse-temurin-21 AS builder-backend
WORKDIR /app
COPY . .
COPY --from=builder-frontend /app/frontend/build /src/main/resources/static
RUN mvn clean package

# Stage 3: Final production image
FROM eclipse-temurin:21-jre-alpine AS production-backend
WORKDIR /app
COPY --from=builder-backend /app/target/dockermastery-0.0.1-SNAPSHOT.jar /app/dockermastery.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dockermastery.jar"]