FROM openjdk:8-jdk-alpine

WORKDIR /app

RUN apk add curl bash zip gradle
RUN curl -s "https://get.sdkman.io" | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install kotlin 1.7.10"
RUN java -version
