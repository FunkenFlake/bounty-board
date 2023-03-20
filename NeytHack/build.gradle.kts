import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.20.0")
    }
}

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

apply(plugin = "kotlinx-atomicfu")

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation ("io.ktor:ktor-client-core:1.6.2")
    implementation ("io.ktor:ktor-client-cio:1.6.2")
    testImplementation(kotlin("test"))
    implementation("io.arrow-kt:arrow-core:1.0.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}