FROM openjdk:11.0.10

RUN mkdir /app

WORKDIR /app

COPY ./build/lib/yorijori-api-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "*.jar"]