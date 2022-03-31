import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val cucumberRuntime: Configuration by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    java
}

group = "us.plp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java:7.2.3")
    testImplementation("io.cucumber:cucumber-junit:7.2.3")
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
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
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