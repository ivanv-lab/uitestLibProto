#!/bin/bash

echo Начало работы скрипта

rm -rf /tmp/allure-results/*
/app/gradlew clean test allureReport

echo gradlew отработал

allure generate /tmp/allure-results -o /app/allure-report

while true; do
  echo "Скрипт работает..."
  sleep 60
done

echo Закончил работу