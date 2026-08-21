# ---- Build mərhələsi ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Əvvəlcə yalnız pom.xml-i kopyalayırıq ki, dependency-lər cache-lənsin
COPY pom.xml .
RUN mvn -B dependency:go-offline

# İndi bütün mənbə kodunu kopyalayıb build edirik
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---- Run (işə salma) mərhələsi ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Build mərhələsindən hazır jar faylını götürürük
COPY --from=build /build/target/*.jar app.jar

# Yüklənmiş şəkillərin saxlanacağı qovluq
RUN mkdir -p /app/uploads

EXPOSE 9595

ENTRYPOINT ["java", "-jar", "app.jar"]
