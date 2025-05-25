FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw package -DskipTests -B
FROM eclipse-temurin:21-jre-jammy
RUN addgroup --system spring && adduser --system --ingroup spring springuser
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
RUN chown springuser:spring /app/app.jar
USER springuser
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
