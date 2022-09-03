#!/bin/sh

# setting
DATE=$(date "+%Y%m%d")
JAR_FILE_PATH=/home/ec2-user/jars
PROJECT=spring-works-hook-5.4.2

echo "> gradle clean"
./gradlew clean

echo "> gradle build"
./gradlew bootJar

echo "> copy jar backup"
ssh ec2-user@ec2-54-215-190-73.us-west-1.compute.amazonaws.com -i key/AWS_KEY "cp $JAR_FILE_PATH/$PROJECT.jar $JAR_FILE_PATH/$PROJECT.jar_$DATE"

echo "> local jar -> server jar"
scp -P22 -i key/AWS_KEY ./build/libs/$PROJECT.jar ec2-user@ec2-54-215-190-73.us-west-1.compute.amazonaws.com:$JAR_FILE_PATH/

echo "> 배포 시작"
ssh ec2-user@ec2-54-215-190-73.us-west-1.compute.amazonaws.com -i key/AWS_KEY "/home/ec2-user/shell/deploy.sh"
