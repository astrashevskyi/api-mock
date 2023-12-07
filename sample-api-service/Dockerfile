FROM maven:3.8.6-jdk-11-slim AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn -ntp -B package

FROM amazoncorretto:11.0.21-alpine3.18

USER root
WORKDIR /opt/bin
COPY --from=builder /app/target/sample-api-service.jar ./
COPY startup.sh ./
RUN chmod +x startup.sh

EXPOSE 8080
HEALTHCHECK --interval=5s --timeout=5s --retries=10 CMD wget -O- localhost:8080/api/v1 || exit 1

ENTRYPOINT ./startup.sh
