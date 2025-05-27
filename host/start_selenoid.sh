#!/bin/bash

HOST_ID=${HOST_ID:-1}  # Default to 1 if not set
for i in 1 2 3 4; do
  docker run -d --name selenoid-${HOST_ID}-${i} \
    -p 444${i}:4444 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $(pwd)/config:/selenoid/etc \
    -e OVERRIDE_VIDEO_OUTPUT_DIR=/opt/selenoid/video \
    -e OVERRIDE_MAX_SESSIONS=1 \
    aerokube/selenoid:latest-release -conf selenoid/etc/browsers.json -limit 1
done

sleep infinity # Keep the host container running