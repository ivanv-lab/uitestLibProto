#!/bin/bash

HOST_ID=${HOST_ID:-1}
echo "HOST_ID: $HOST_ID"
echo "Starting Selenoid containers for HOST_ID: ${HOST_ID}"
for i in 1 2 3 4; do
  CONTAINER_NAME="selenoid-${HOST_ID}-${i}"
  echo "CONTAINER_NAME: $CONTAINER_NAME"

  # Останавливаем и удаляем существующий контейнер (если он есть)
  echo "Stopping and removing existing container"
  docker stop "$CONTAINER_NAME" 2>/dev/null
  docker rm "$CONTAINER_NAME" 2>/dev/null

  # Определяем внутренний порт
  INTERNAL_PORT=$((4440 + i))
  echo "INTERNAL_PORT: $INTERNAL_PORT"

  echo "Starting $CONTAINER_NAME on port $INTERNAL_PORT"
  docker run -d --name "$CONTAINER_NAME" \
    -p $INTERNAL_PORT:4444  \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $(pwd)/config:/etc/selenoid \
    -e OVERRIDE_VIDEO_OUTPUT_DIR=/opt/selenoid/video \
    -e OVERRIDE_MAX_SESSIONS=1 \
    aerokube/selenoid:latest-release -conf /etc/selenoid/browsers.json -limit 1
  echo "docker run finished"
done

sleep infinity