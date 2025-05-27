FROM ubuntu:latest

RUN apt-get update && apt-get install -y curl docker.io

WORKDIR /app

COPY start_selenoid.sh .
RUN chmod +x start_selenoid.sh

CMD ["./start_selenoid.sh"]