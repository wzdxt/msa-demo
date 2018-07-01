#!/usr/bin/env bash

set -v

ROOT_DIR=$(dirname $(dirname $(dirname $(realpath $0))))

echo root path is: ${ROOT_DIR}

java $* -jar ${ROOT_DIR}/service-product/target/service-product.jar

