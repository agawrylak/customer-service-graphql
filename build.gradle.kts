import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
	application
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

apply(plugin = "io.spring.dependency-management")

group = "edu.agawrylak"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:latest.release")
	implementation("com.netflix.graphql.dgs:graphql-dgs-subscriptions-websockets-autoconfigure:latest.release")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

application {
	// Set the mainClass property early before it's accessed by any task
	mainClass.set("edu.agawrylak.customerservicegraphql.CustomerServiceGraphqlApplicationKt")
}

tasks.withType<ShadowJar> {
	archiveClassifier.set("standalone")
	manifest {
		attributes("Main-Class" to application.mainClass.get())
	}
	// Add the runtime classpath to the ShadowJar
	from(project.configurations.runtimeClasspath.get().map { it })
}
