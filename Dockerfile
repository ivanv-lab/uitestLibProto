FROM gradle:8.7.0-jdk17

WORKDIR /app

COPY . .

CMD ["gradle", "test"] # Команда запуска тестов