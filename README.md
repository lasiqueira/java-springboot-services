# user
Java 16 and Spring boot 2 CRUD REST API

## Setup

You need:

[JDK16+](https://jdk.java.net/)

[Gradle 7.0.2+](https://gradle.org/releases/)
(Alternatively you can use the gradle wrapper with `gradlew` instead of the `gradle` command)

to build and run this project.

## Building

Run `gradle clean build` in the command line interface.

To build the docker image, run `gradle bootBuildImage` in the command line interface.

## Testing

Run `gradle clean test` in the command line interface.


## Running
To run dockerised, run `docker-compose up` in the command line interface.
To run locally, run `gradle bootRun` in the command line interface.


## Documentation
(default: http://localhost:8080)
server:port/swagger-ui.html
