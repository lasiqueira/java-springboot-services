# user
Java 25 and Spring Boot 4 CRUD REST API

## Setup

You need [JDK 25](https://adoptium.net/) to build and run this project
(e.g. `brew install openjdk@25`). Gradle is provided by the wrapper (`./gradlew`, Gradle 9.9).

## Building

Run `./gradlew clean build` in the command line interface.

To build the docker image, run `./gradlew bootBuildImage` in the command line interface.

## Testing

Run `./gradlew clean test` in the command line interface.


## Running
To run dockerised, build the jar and run `docker-compose up` in the command line interface.
To run locally, run `./gradlew bootRun` in the command line interface.


## Documentation
(default: http://localhost:8080)
server:port/swagger-ui.html
