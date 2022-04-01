FROM node:17-alpine as client-builder
COPY client /client
WORKDIR /client
RUN npm install -q && npm run build

FROM --platform=linux/amd64 openjdk:17-jdk-alpine as server-builder
COPY server /server
WORKDIR /server
COPY --from=client-builder /client/dist/graphql-client/ src/main/resources/static/
RUN ./gradlew clean build

FROM --platform=linux/amd64 openjdk:17-jdk-alpine
MAINTAINER serhii.skotarenko@gmail.com
COPY --from=server-builder /server/build/libs/graphql-file-upload-0.0.1-SNAPSHOT.jar graphql-file-upload-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/graphql-file-upload-0.0.1-SNAPSHOT.jar"]
