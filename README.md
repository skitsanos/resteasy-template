# RESTEasy Undertow Starter

Lightweight starter kit for building Java microservices with RESTEasy on embedded Undertow. Ships as a single executable JAR—no external servlet container required.

## Features
- Jakarta REST (RESTEasy 7) with Jackson 2 JSON provider
- Embedded Undertow HTTP server; starts with `java -jar …`
- Simple example endpoints: root info, health check, and header echo
- Java 17 baseline, shaded fat-jar packaging

## Quick start
Prereqs: JDK 17+ and Maven.

```bash
mvn -q package
java -jar target/restapi-2.0.1.jar
```

Endpoints (default port 8080):
- `/` — service and runtime info (includes build version)
- `/info` — basic health check
- `/health` — JSON status indicator (`{"status":"UP"}`)
- `/echo` — returns request header names

## Development
- Run tests: `mvn test`
- Update version: edit `pom.xml` `<version>` (propagates into manifest and `/` response)

## License
MIT
