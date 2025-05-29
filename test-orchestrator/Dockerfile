FROM gradle:7.4.0-jdk17
WORKDIR /app
COPY . .
RUN chmod +x gradlew

RUN apt-get update && apt-get install -y wget unzip
RUN wget https://github.com/allure-framework/allure2/releases/download/2.34.0/allure-2.34.0.zip
RUN unzip allure-2.34.0.zip
RUN mv allure-2.34.0 /opt/allure
RUN ln -s /opt/allure/bin/allure /usr/local/bin/allure

ENV PATH="$PATH:/opt/allure/bin"

RUN chmod +x test-orchestrator/run_tests.sh
# CMD ./gradlew clean test allureReport && allure serve /build/allure-results
ENTRYPOINT ["/app/test-orchestrator/run_tests.sh"]