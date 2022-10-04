#!/bin/sh
dockerNetwork="springbankNet"
docker network inspect $dockerNetwork > /dev/null || \
docker network create --attachable -d bridge $dockerNetwork