#!/bin/bash

HOST_ID=${HOST_ID:-1}
echo "Starting Selenoid containers for HOST_ID: ${HOST_ID}"
for i in 1 2 3 4; do
  CONTAINER_NAME="selenoid-${HOST_ID}-${i}"

  # Останавливаем и удаляем существующий контейнер (если он есть)
  docker stop "$CONTAINER_NAME" 2>/dev/null  # Игнорируем ошибки, если контейнера нет
  docker rm "$CONTAINER_NAME" 2>/dev/null

  echo "Starting $CONTAINER_NAME"
  docker run -d --name "$CONTAINER_NAME" \
    -p 444${i}:4444 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $(pwd)/config:/etc/selenoid \
    -e OVERRIDE_VIDEO_OUTPUT_DIR=/opt/selenoid/video \
    -e OVERRIDE_MAX_SESSIONS=1 \
    aerokube/selenoid:latest-release -conf /etc/selenoid/browsers.json -limit 1
done

sleep infinity