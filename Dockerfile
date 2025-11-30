# Multi-stage Dockerfile: build with JDK, run with JRE
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy sources
COPY src ./src
COPY lib ./lib

RUN mkdir out \
    && echo "Compiling Java sources..." \
    && javac -d out $(/bin/sh -c 'find src -name "*.java"') \
    && printf "Main-Class: dynamic_beat_17.Main\n" > manifest.txt \
    && jar cfm app.jar manifest.txt -C out . \
    && if [ -d lib ] ; then for dep in lib/*.jar; do TMPDIR=$(mktemp -d) && (cd "$TMPDIR" && jar xf "/app/$dep") && jar uf app.jar -C "$TMPDIR" . && rm -rf "$TMPDIR"; done; fi \
    && jar uf app.jar -C src images -C src music || true \
    && mv app.jar /app/dist.jar

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/dist.jar /app/dist.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/dist.jar"]
