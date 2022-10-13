FROM eclipse-temurin:17-jdk-jammy as builder
RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
USER demo
WORKDIR /app
ADD .mvn/ .mvn
ADD mvnw ./
ADD pom.xml ./
RUN ./mvnw dependency:go-offline
ADD ./src ./src
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy
RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
USER demo
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar" ]