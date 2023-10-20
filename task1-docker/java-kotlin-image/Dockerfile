# Base image 
FROM ubuntu:22.04

# Install prerequisites and Java 8
RUN apt-get update && apt-get install -y software-properties-common apt-utils wget unzip openjdk-8-jdk

# Download Kotlin compiler from url, unzip file, move compiler and delete
RUN wget -q https://github.com/JetBrains/kotlin/releases/download/v1.9.10/kotlin-compiler-1.9.10.zip \
  && unzip kotlin-compiler-1.9.10.zip \
  && mv kotlinc /usr/local/bin \
  && rm kotlin-compiler-1.9.10.zip

# Add directory to allow run kotlinc from anywhere in the system
ENV PATH=$PATH:/usr/local/bin/kotlinc/bin

# Display installed Java version
RUN java -version

# Display installed Kotlin version
RUN kotlinc -version

# Download and unzip Gradle in /opt directory
RUN wget -q https://services.gradle.org/distributions/gradle-8.4-bin.zip -P /tmp \
  && unzip -d /opt/gradle /tmp/gradle-*.zip \
  && rm /tmp/gradle-*.zip

# Config system environment
ENV PATH=$PATH:/opt/gradle/gradle-8.4/bin

# Display Gradle version
RUN gradle -v

# Set wokring directory
WORKDIR /app

COPY ./src /app/src
COPY build.gradle /app

RUN gradle build

CMD ["gradle", "run"]