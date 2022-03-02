import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val cucumberRuntime: Configuration by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "us.plp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.cucumber:cucumber-java:7.2.3")
    implementation("io.cucumber:cucumber-junit:7.2.3")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.2.3")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

task("cucumber") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            // Change glue for your project package where the step definitions are.
            // And where the feature files are.
            args = listOf("--plugin", "pretty", "--glue", "us.plp.integrations", "src/test/resources/cucumber/features")
        }
    }
}

application {
    mainClass.set("MainKt")
}