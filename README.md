# File upload with Angular and GraphQL

## Client app
- Angular
- Apollo GraphQL

To start the client in development mode run 
```shell
ng serve
```
from the client directory.

## Server app
- Java
- Spring Boot
- Netflix DGS

To start the client in development mode run the main class.

## Demo mode
To start the app (both client and server) in demo mode using Docker:

1. Build image:
```shell
docker build -t graphql-file-upload:1 .
```
2. Start container
```shell
 docker run -p 8080:8080 graphql-file-upload:1
```
3. Open http://localhost:8080 in browser.
