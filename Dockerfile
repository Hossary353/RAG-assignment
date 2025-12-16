FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests
FROM eclipse-temurin:17-jre-focal AS final

RUN groupadd --system appuser && useradd --system --gid appuser appuser
USER appuser

EXPOSE 8081

ARG JAR_FILE=target/*.jar

COPY --from=build /app/${JAR_FILE} assignment-1.0.jar

ENTRYPOINT ["java", "-jar", "/assignment-1.0.jar"]
