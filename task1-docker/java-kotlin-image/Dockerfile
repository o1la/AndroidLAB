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
ENV PATH $PATH:/usr/local/bin/kotlinc/bin

# Display installed Java version
RUN java -version

# Display installed Kotlin version
RUN kotlinc -version
