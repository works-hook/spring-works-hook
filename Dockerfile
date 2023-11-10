# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함 (파일 올릴 때 마다 gradle 다운로드 안하게 캐시 작업)
# COPY build.gradle.kts settings.gradle.kts /build/
# RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

FROM gradle:jdk11
COPY ./build/libs/spring-works-hook-5.4.2.jar spring-works-hook.jar

EXPOSE 8080
ENV	USE_PROFILE local

USER nobody
ENTRYPOINT [\
   "java", "-jar",\
   "-Dspring.profiles.active=${USE_PROFILE}",\
   "spring-works-hook.jar"\
]
