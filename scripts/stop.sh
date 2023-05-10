#!/bin/bash

ROOT_PATH="/home/ubuntu/app"
APPLICATION_JAR="$ROOT_PATH/build/libs/Meme_NameKing-0.0.1-SNAPSHOT.jar"
STOP_LOG="$ROOT_PATH/stop.log"
SERVICE_PID=$(pgrep -f $APPLICATION_JAR) # 실행 중인 Spring Boot 애플리케이션의 PID

if [ -z "$SERVICE_PID" ]; then
echo "현재 실행 중인 서비스가 없습니다." >> $STOP_LOG
else
echo "서비스를 종료합니다." >> $STOP_LOG
kill "$SERVICE_PID"
# kill -9 $SERVICE_PID # 강제 종료를 원할 경우 이 명령어를 사용하세요.
fi
