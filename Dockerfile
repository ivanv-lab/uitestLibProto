FROM gradle:8.7.0-jdk17

WORKDIR /app

COPY . .

ENV JAVA_TOOL_OPTIONS "-Dfile.encoding=UTF-8"

CMD ["gradle", "test", "-Dfile.encoding=UTF-8"]

