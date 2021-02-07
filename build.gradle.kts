import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
}

group = "edu.umontreal"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jooq:jooq:3.12.1")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")
    compile("org.jetbrains.kotlin:kotlin-scripting-jvm")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
//            apiVersion = "1.5"
//            languageVersion = "1.5"
            jvmTarget = "1.8"
        }
    }

    register("main", JavaExec::class) {
        main = "PresentationKt"
        classpath = sourceSets["main"].runtimeClasspath
        description = "Runs demo script"
    }
}