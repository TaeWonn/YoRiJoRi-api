FROM openjdk:11.0.10

RUN mkdir /app

WORKDIR /app

COPY ./build/libs/yorijori-api-0.0.1-SNAPSHOT.jar /app/yorijori.jar

CMD ["java", "-jar", "/app/yorijori.jar"]