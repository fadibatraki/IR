# === Stage 1: Build the app ===
FROM eclipse-temurin:21-jdk-alpine as builder

RUN apk add --no-cache bash

WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# === Stage 2: Run the app ===
FROM eclipse-temurin:21-jdk-alpine

RUN adduser -D springuser
USER springuser

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]

