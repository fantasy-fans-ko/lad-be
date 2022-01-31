import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jmailen.kotlinter") version "3.2.0"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    kotlin("kapt") version "1.4.10"
}

group = "com.fantasy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")
    implementation("org.flywaydb:flyway-core")

    // Scraping
    implementation("org.jsoup:jsoup:1.13.1")

    // Web Socket
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-websocket")
    implementation ("org.webjars:webjars-locator-core")
    implementation ("org.webjars:sockjs-client:1.0.2")
    implementation ("org.webjars:stomp-websocket:2.3.3")
    implementation ("org.webjars:bootstrap:3.3.7")
    implementation ("org.webjars:jquery:3.1.1-1")

    // Test & Add..
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    compileOnly("com.google.code.gson:gson")

    // JWT
    compileOnly(group = "io.jsonwebtoken", name = "jjwt-api", version = "0.11.2")
    runtimeOnly(group = "io.jsonwebtoken", name = "jjwt-impl", version = "0.11.2")
    runtimeOnly(group = "io.jsonwebtoken", name = "jjwt-jackson", version = "0.11.2")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // QueryDsl
    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

    sourceSets.main {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDir("$buildDir/generated/source/kapt/main")
        }
    }
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
