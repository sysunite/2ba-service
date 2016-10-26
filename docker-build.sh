#!/usr/bin/env bash

# Run this script in a Docker Terminal
docker build -t sysunite/2ba-service:0.0.3 .
docker push sysunite/2ba-service:0.0.3