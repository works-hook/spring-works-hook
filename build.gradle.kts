@file:Suppress("DEPRECATION")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
    }
}

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    // code style
    // https://cheese10yun.github.io/ktlint/
    // https://msyu1207.tistory.com/entry/%EA%B9%94%EB%81%94%ED%95%9C-%ED%8F%AC%EB%A7%B7%ED%8C%85%EC%9D%84-%EC%9C%84%ED%95%9C-ktlint-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0-feat-kotlin
    // https://seosh817.tistory.com/154

    // git hooks error
    // https://github.com/JLLeitschuh/ktlint-gradle/issues/562
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"

    // sonarqube
    id("org.sonarqube") version "3.4.0.2513"

    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("plugin.allopen") version "1.4.32"

    // mapStruct
    kotlin("kapt") version "1.6.21"
}

group = "com.example"
version = "5.4.2"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("javax.validation:validation-api:2.0.1.Final")

    // kotlin test
    testImplementation("io.kotest:kotest-runner-junit5:$version")
    testImplementation("io.mockk:mockk:1.12.7")

    // database
    runtimeOnly("com.h2database:h2") // test
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client") // develop

    // JPA - JSON in MySQL, message converter
    implementation("com.vladmihalcea:hibernate-types-52:2.18.0")

    // json parsing
    implementation("org.json:json:20220320")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")

    // api
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.EntityListeners")
    annotation("org.springframework.data.annotation.CreatedDate")
    annotation("org.springframework.data.jpa.domain.support.AuditingEntityListener")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// querydsl
sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}
