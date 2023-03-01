import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "dev.robinsond.chore-management"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.data:spring-data-mongodb")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
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

tasks.named("processResources") {
    dependsOn("copyFrontend")
}

tasks {
    register<Exec>("installFrontend") {
        inputs.file(file("chore-management-ui/yarn.lock"))
        inputs.file(file("chore-management-ui/package.json"))
        outputs.dir(file("chore-management-ui/node_modules"))
        commandLine("yarn", "--cwd", "chore-management-ui", "install")
        doLast {
            println("Frontend dependencies installed")
        }
    }

    register<Exec>("buildFrontend") {
        dependsOn("installFrontend")
        inputs.dir("chore-management-ui")
        outputs.dir("chore-management-ui/build")
        commandLine("yarn", "--cwd", "chore-management-ui", "build")
        doLast {
            println("Frontend built")
        }

    }

    register<Sync>("copyFrontend") {
        dependsOn("buildFrontend")
        from("chore-management-ui/build")
        into("src/main/resources/static")

        doLast {
            println("Frontend directory copied to public directory")
        }
    }
}
