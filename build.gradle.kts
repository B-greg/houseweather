plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.9.25" // or latest Kotlin version
	war
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.gregb"
version = "0.0.1-SNAPSHOT"
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

kapt {
	correctErrorTypes = true // Helps with MapStruct type resolution
	arguments {
		arg("mapstruct.defaultComponentModel", "spring") // Auto-generate Spring beans
	}
}

repositories {
	mavenCentral()
}

dependencyManagement {
	applyMavenExclusions(false)
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.xerial:sqlite-jdbc:3.42.0.0")
	implementation("org.hibernate.orm:hibernate-community-dialects")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.thymeleaf.extras:thymeleaf-extras-java8time")
	implementation("com.github.thanglequoc:aqi-calculator:1.3.1")
	implementation("org.springframework.integration:spring-integration-mqtt:6.4.1")
	implementation("org.mapstruct.extensions.spring:mapstruct-spring-annotations:0.1.2")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
