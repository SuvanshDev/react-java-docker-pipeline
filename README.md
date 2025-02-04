# react-java-docker-pipeline
"A containerized full-stack web app (Java backend, React frontend) with automated CI/CD using GitHub Actions and Docker Hub."
======
## Environment Variables

This project uses below environment variables:
```shell
DB_URL #Example: jdbc:mysql://localhost:3306/dockermastery Syntax: jdbc:mysql://<hostname>:<port>/<db_name>
DB_USERNAME # Username of databases Example: root
DB_PASSWORD # Password of database Example: root12345
```

Make sure your DB is up and running and an empty database is created.

For example, if you have a mysql instance running locally at port 3306, connect to it and create a new database schema using below command:
```sql
CREATE DATABASE dockermastery;
```

The DB URL will become `jdbc:mysql://localhost:3306/dockermastery`

## Build Steps

This project uses JAVA 21 and Maven. Make sure you have that installed in the system.

This project has an inbuilt Maven wrapper `mvnw` that can be used to build, test, and run the project.

To Run this project, below command can be used:
```shell
./mvnw spring-boot:run
```
or
```shell
mvn spring-boot:run
```

The Run will fail if above environment variables are not set. 

You can pass these environment variables as below:

On linux/macOs
```shell
export DB_URL="jdbc:mysql://localhost:3306/dockermastery"
export DB_USERNAME=root
export DB_PASSWORD=root
```

On Windows:
```batch
set DB_URL=jdbc:mysql://localhost:3306/dockermastery
set DB_USERNAME=root
set DB_PASSWORD=root
```

As the application starts, it creates a table called `todo` and add a few rows to it.
The application runs at port `8080`

Once the application is started, you can go to your browser and go to url `http://localhost:8080/api/todos`. This will return the following response on your browser:
```json
{
  "todos": [
    {
      "id": 1,
      "title": "Finish Assignment",
      "description": "Complete the assignment on database management systems.",
      "status": "pending"
    },
    {
      "id": 2,
      "title": "Grocery Shopping",
      "description": "Buy milk, eggs, and bread from the supermarket.",
      "status": "completed"
    }
  ]
}
```

If you are getting this output, your application is running correctly.


## Packaging

Application can be compiled and packed in a jar using the below command:
```shell
./mvnw clean package
```

This command outputs the jar inside `target` folder with name `dockermastery-0.0.1-SNAPSHOT.jar`.

To run this jar, you can use below command:

```shell
java -jar dockermastery-0.0.1-SNAPSHOT.jar
```
Make sure your environment variables are set before running this jar. 

## Running tests

This application contains unit tests that can be run by 
```shell
./mvnw test
```

Make sure all tests are passed before running the application.

## Building Frontend

This application contains a react frontend under `frontend` folder. To build this, make sure you have `npm` and `nodejs` installed on your system.
To run it locally, open the `frontend` folder using below command:
```shell
cd frontend
```
Now, install all the frontend `nodejs` dependencies as below:
```shell
npm install
```

Start the frontend service:
```shell
npm start
```

If all the steps are successful, a page will open in the browser at hostname `http://localhost:3000`. This page will communicate with the backend APIs `/api/todos` to display the available TODOs. If your backend service is not running, it will result in empty TODOs.

In order to build the frontend, run below command:
```shell
npm run build
```
This command will generate some files inside `frontend/build` folder. **Copy all the content of `frontend/build` folder to `src/main/resources/static`**. Now your spring boot application will serve the frontend instead of a separate `npm` server.

You can now shut down the frontend service and restart the application using `./mvnw spring-boot:run` command. Go to your browser and type `http://localhost:8080` and it will open the frontend page listing all the available todos.
