# Stage 1: Build the application
FROM maven:3.8.6-eclipse-temurin-17 AS build

WORKDIR /app

# Копирование файлов Maven и установка зависимостей
COPY pom.xml .
RUN mvn dependency:resolve

# Копирование исходного кода
COPY src ./src

# Сборка проекта с полным логированием
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Копирование скомпилированного JAR файла из предыдущего stage
COPY --from=build /app/target/*.jar app.jar

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
