# biometrics
Java 25 and Spring Boot 4 CRUD REST API

## Setup
You need [JDK 25](https://adoptium.net/) (e.g. `brew install openjdk@25`). Gradle is provided by the wrapper.

## Building
`./gradlew clean build`

## Running
Needs RabbitMQ on `localhost:5672` (guest/guest), e.g. `docker run -p 5672:5672 rabbitmq:4`.
Messages are exchanged as JSON.

`./gradlew bootRun`

## Documentation
(default: http://localhost:8080)
server:port/swagger-ui.html
