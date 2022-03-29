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
    testImplementation ("com.github.stefanbirkner:system-lambda:1.2.0")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

task("cucumber") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            // Change glue for your project package where the step definitions are.
            // And where the feature files are.
            args = listOf("--plugin", "pretty", "--glue", "us.plp.bankaccount.integrations", "src/test/resources/cucumber/features")
        }
    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    // To add all of the dependencies otherwise a "NoClassDefFoundError" error
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

application {
    mainClass.set("MainKt")
}