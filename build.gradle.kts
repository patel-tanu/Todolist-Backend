import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

tasks.named<GenerateTask>("openApiGenerate") {
	generatorName.set("kotlin-spring") // ✅ Kotlin Spring Boot code generation
	inputSpec.set("$projectDir/src/main/resources/todo-api.yaml") // ✅ Path to OpenAPI YAML file
	outputDir.set(layout.buildDirectory.dir("generated").get().asFile.path) // ✅ Output directory
	apiPackage.set("com.example.todo.api") // ✅ Change as needed
	modelPackage.set("com.example.todo.model") // ✅ Change as needed
	configOptions.set(
		mapOf(
			"dateLibrary" to "java8",
			"useSpringBoot3" to "true",
			"interfaceOnly" to "true"
		)
	)
}

// ✅ Ensure the generated code is included in the project
sourceSets {
	main {
		kotlin.srcDir(layout.buildDirectory.dir("generated/src/main/kotlin").get().asFile.path)
	}
}



plugins {
	application
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.3.0"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}





//dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	runtimeOnly("org.postgresql:postgresql")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//}

dependencies {
	// Spring Boot & Web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Kotlin Support
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Database (PostgreSQL + JPA)
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.postgresql:postgresql:42.7.2")

	// OpenAPI (Swagger)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

	// OpenAPI Generator
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
}







kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}


application {
	mainClass.set("com.example.todo.TodoAppApplicationKt")
}
