FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests
FROM eclipse-temurin:21-jre-jammy
RUN addgroup --system spring && adduser --system --ingroup spring springuser
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
RUN chown springuser:spring /app/app.jar
USER springuser
EXPOSE 8081
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
