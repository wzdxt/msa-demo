#!/usr/bin/env bash

set -v

ROOT_DIR=$(dirname $(dirname $(dirname $(realpath $0))))

echo root path is: ${ROOT_DIR}

java $* -jar ${ROOT_DIR}/oauth2-server/target/oauth2-server.jar

