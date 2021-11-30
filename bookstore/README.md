Bookstore
=========

Required tools
--------------

- For the backend:
  - Java 11 or newer
  - IntelliJ IDEA or whatever you prefer
- For the frontend:
  - Node.js and npm
  - Visual Studio Code or whatever you prefer
- Docker and Docker Compose

Project setup
-------------

After cloning, run `./mvnw install` in the root directory and then import the code as Maven project in your IDE.
Additionally, for the Angular frontend application, run `npm install` from within the "frontend" directory.

Running infrastructure services
-------------------------------

Infrastructure services such as Zipkin, Graylog, etc. need to be started prior to starting the component services.
These are configured in a Docker Compose file called "docker-compose.yml" in the root directory and can be started
with `docker-compose up -d`.

Running component services
--------------------------

### Backend

The following components run as services:
- api
- catalog
- order
- payment
- rating
- recommendation
- shipping

In order to start a component service, locate its `@SpringBootApplication`-annotated Java class inside its "service"
submodule and run it.

### Frontend

The "frontend" can be started by executing `npm start` from within the "frontend" directory.
