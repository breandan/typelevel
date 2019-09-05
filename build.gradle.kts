import org.jetbrains.intellij.tasks.RunIdeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    id("org.jetbrains.intellij") version "0.4.10"
}

group = "edu.umontreal"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
//    maven("https://maven.pkg.github.com/breandan/kotlingrad") {
//        credentials {
//            username = project.properties["githubUsername"]?.toString()
//            password = project.properties["githubToken"]?.toString()
//        }
//    }
}

dependencies {
    implementation("org.jooq:jooq:3.12.1")
    implementation(kotlin("stdlib-jdk8"))
//    implementation("edu.umontreal:kotlingrad:0.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")
    compile("org.jetbrains.kotlin:kotlin-scripting-jvm")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    register("main", JavaExec::class) {
        main = "PresentationKt"
        classpath = sourceSets["main"].runtimeClasspath
        description = "Runs demo script"
    }

    withType<RunIdeTask> {
        args = listOf(projectDir.absolutePath)
    }
}

intellij {
    downloadSources = false
}
