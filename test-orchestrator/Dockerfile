FROM gradle:8.7.0-jdk17

WORKDIR /app

# Копируем все файлы проекта, включая тесты
COPY . .

# Раскомментируйте, если нужно загрузить зависимости при построении образа
# RUN gradle dependencies

# Копируем скрипт запуска тестов
COPY run_tests.sh .
COPY gradlew .
RUN chmod +x run_tests.sh

# Запускаем тесты
CMD ["./run_tests.sh"]