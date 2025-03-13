FROM amazoncorretto:17

ARG VERSION=1.0.0

ADD core/target/core-$VERSION.jar game-session-service.jar

ENTRYPOINT ["java", "-jar", "game-session-service.jar"]
EXPOSE 8080
