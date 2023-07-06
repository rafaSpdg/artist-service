FROM maven:3.9.2-eclipse-temurin AS builder

WORKDIR /opt/artist-service

ADD pom.xml .

RUN mvn dependency:resolve

ADD src src

RUN mvn clean install


FROM eclipse-temurin AS release

WORKDIR /opt/artist-service

COPY --from=builder /opt/artist-service/target/artist-service-0.0.1-SNAPSHOT.jar artist-service.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar artist-service.jar"]