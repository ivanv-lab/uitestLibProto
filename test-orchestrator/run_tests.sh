#!/bin/bash
echo Начало работы скрипта
/app/gradlew test allureReport
echo gradlew отработал
#/opt/allure/bin/allure serve --port 35277 /app/build/allure-results
allure generate /app/build/allure-results -o /app/allure-report
while true; do
  # Здесь могут быть какие-то действия, которые нужно выполнять периодически
  # Например, проверка состояния системы, логирование, и т.п.
  echo "Скрипт работает..."
  sleep 60  # Пауза в 60 секунд (1 минута)
done
echo Закончил работу