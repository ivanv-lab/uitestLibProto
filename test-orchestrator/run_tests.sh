#!/bin/bash
# Скрипт для запуска тестов

# Получаем список Selenoid URLs из переменной окружения
SELENOID_URLS=${SELENOID_URLS:-"http://localhost:5444/wd/hub,http://localhost:5445/wd/hub,http://localhost:5446/wd/hub,http://localhost:5447/wd/hub"}
SELENOID_URLS_ARRAY=($(echo $SELENOID_URLS | tr ',' '\n'))

# Функция для запуска тестов на одном Selenoid URL
run_tests_on_selenoid() {
  SELENOID_URL=$1
  echo "Running tests on $SELENOID_URL"
  # Передаем Selenoid URL как системное свойство
  ./gradlew test -Dselenoid.url="$SELENOID_URL" --tests "*"
  # TODO: Обработка результатов тестов и вывод
}

# Запускаем тесты параллельно на каждом Selenoid URL
for SELENOID_URL in "${SELENOID_URLS_ARRAY[@]}"; do
  run_tests_on_selenoid "$SELENOID_URL" &
done

wait # Ждем завершения всех процессов
echo "All tests finished"