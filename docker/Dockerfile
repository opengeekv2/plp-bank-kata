FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

COPY --from=gradle:7-jdk17 /opt/gradle /opt/gradle
RUN ln -s /opt/gradle/bin/gradle /usr/bin/gradle
WORKDIR /app
ENTRYPOINT gradle build && java -jar build/libs/plp-bank-kata-1.0-SNAPSHOT.jar