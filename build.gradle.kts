import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.8.0-Beta"
}

group = "edu.umontreal"
version = "1.0-SNAPSHOT"

repositories.mavenCentral()

dependencies {
    implementation("org.jooq:jooq:3.17.5")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            apiVersion = "1.5"
            languageVersion = "1.5"
            jvmTarget = "1.8"
        }
    }

    register("main", JavaExec::class) {
        main = "PresentationKt"
        classpath = sourceSets["main"].runtimeClasspath
        description = "Runs demo script"
    }
}