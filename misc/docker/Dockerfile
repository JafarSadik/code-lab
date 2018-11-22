FROM gradle:4.10-jdk11-slim as build_stage
COPY --chown=gradle:gradle . /project/
WORKDIR /project
RUN gradle build

FROM openjdk:11-slim
WORKDIR /app
COPY --from=build_stage /project/build/libs/docker-spark.jar .

EXPOSE 8080

CMD java -jar docker-spark.jar