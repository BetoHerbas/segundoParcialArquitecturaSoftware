FROM ubuntu:latest
LABEL authors="beto"

ENTRYPOINT ["top", "-b"]