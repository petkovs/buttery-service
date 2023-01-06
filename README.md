# Battery Service

The battery service application provides ...

## Prerequisites

* Java 11 - Runtime and build requires Java 11.

## Build

### Java Build

1. Clone the git repo ``
2. `cd battery-service`
3. `./gradlew clean build`

## Running

### Run as a standalone Spring Boot service

Start the Spring Boot service (replace `${version}` with the current version from `build.gradle`) in another console window

```bash
java -jar buttery-service-${version}.jar
```

## Runtime Configuration

### Port Binding

The service runs on port 8080 by default. To start on another port set the `server.port` system property to the preferred port number.

### Profiles

Select a runtime profile by setting the `spring.profiles.active` system property.

e.g. `java -Dspring.profiles.active=development`

#### Development Profile

## API Documentation

API's are documented online. With the service running in standalone mode, go to: http://localhost:8080/buttery-service/api/swagger-ui.html . Remember to change the port number in the URL if running on a port other than the default (8080).

## Maintenance

Change the version as necessary (see `build.gradle` for the version number).
