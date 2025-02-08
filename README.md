# 🚀 React + Java Docker Pipeline

## 🌟 Project Overview

This project is a **full-stack application** with a **Java Spring Boot backend** and a **React frontend**, containerized using **Docker**. It demonstrates:

✅ Multi-stage **Docker builds** for optimized images  
✅ **CI/CD automation** using **GitHub Actions**  
✅ **Docker Hub integration** with automated image tagging using commit hash  
✅ **MySQL database** containerized for easy local setup  

---

## 🏗️ Dockerization Process

### 🛠️ 1. Setting up the Database
Run MySQL inside a Docker container:
```sh
docker network create todo

docker run --name tododb -d -p 3306:3306 --network=todo \
-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dockermastery mysql:latest
```

### 🖥️ 2. Running the Application Locally
**Backend:**
```sh
mvn spring-boot:run
```
**Frontend:**
```sh
cd frontend  
npm install  
npm start
```

The run will fail if environment variables are not set. 
You can pass these environment variables as below:
```sh
set DB_URL=jdbc:mysql://localhost:3306/dockermastery
set DB_USERNAME=root
set DB_PASSWORD=root
```

### 📦 3. Multi-Stage Dockerfile
```dockerfile
# Frontend Build
FROM node:18 AS builder-frontend
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build

# Backend Build
FROM maven:3.9.9-eclipse-temurin-21 AS builder-backend
WORKDIR /app
COPY . .
COPY --from=builder-frontend /app/frontend/build /src/main/resources/static
RUN mvn clean package

# Final Runtime Image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder-backend /app/target/dockermastery-0.0.1-SNAPSHOT.jar /app/dockermastery.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dockermastery.jar"]
```

### 🚀 4. Running the Docker Container
```sh
docker build -t todo-prod .
docker run -it --network=todo -p 8081:8080 \
-e DB_URL=jdbc:mysql://tododb:3306/dockermastery \
-e DB_USERNAME=root -e DB_PASSWORD=root todo-prod
```

---

## 🔄 CI/CD Pipeline with GitHub Actions
This project uses **GitHub Actions** to automate Docker builds and push images to **Docker Hub**.

### 📌 Workflow Steps
1️⃣ **Triggers**: Runs on push to `develop` branch.  
2️⃣ **Build & Tag**: Extracts commit hash and builds the image.  
3️⃣ **Push to Docker Hub**: Uses credentials from GitHub Secrets.  

#### 📜 GitHub Actions Workflow (`.github/workflows/docker-pipeline.yml`)
```yaml
name: Build and Push Docker Image

on:
  push:
    branches:
      - develop

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Code
        uses: actions/checkout@v4

      - name: Setup Docker Build
        uses: docker/setup-buildx-action@v3

      - name: Login to Docker Hub
        uses: docker/login-action@v3
        with:
          username: ${{ secrets.DOCKERHUB_USERNAME }}
          password: ${{ secrets.DOCKERHUB_PASSWORD }}

      - name: Extract Git Commit Hash
        run: echo "commit_hash=$(git rev-parse --short HEAD)" >> $GITHUB_ENV

      - name: Build and push
        uses: docker/build-push-action@v6
        with:
          push: true
          tags: fullstacktechie/react_java_docker_pipeline:${{env.commit_hash}}
```

---

## 🏁 How to Run Locally or Deploy

### 🔹 Running Locally
1. Clone the repository:  
   ```sh
   git clone https://github.com/SuvanshDev/react-java-docker-pipeline.git
   cd react-java-docker-pipeline
   ```
2. Start the MySQL database (as mentioned above).  
3. Build and run the backend/frontend locally.  

### 🐳 Running with Docker
```sh
docker build -t todo-prod .
docker run -it --network=todo -p 8081:8080 -e DB_URL=jdbc:mysql://tododb:3306/dockermastery \
-e DB_USERNAME=root -e DB_PASSWORD=root todo-prod
```

### 🚀 Deploying with GitHub Actions
- Push changes to the `develop` branch.  
- GitHub Actions will automatically build and push the image to **Docker Hub** : https://hub.docker.com/repository/docker/fullstacktechie/react_java_docker_pipeline/general 

---



