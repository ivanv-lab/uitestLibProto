#!/bin/bash
echo Начало работы скрипта
/app/gradlew clean test allureReport
echo Запустил gradlew
/opt/allure/bin/allure serve /app/build/allure-results
echo Закончил работу