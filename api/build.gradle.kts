import com.expediagroup.graphql.plugin.gradle.graphql
import org.gradle.kotlin.dsl.flyway
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.expediagroup.graphql") version "5.1.0"
    id("org.flywaydb.flyway") version "8.0.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "com.okeicalm"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

val graphqlKotlinVersion = "5.1.0"
val exposedVersion = "0.37.3"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.expediagroup", "graphql-kotlin-spring-server", graphqlKotlinVersion)
    implementation("com.expediagroup", "graphql-kotlin-schema-generator", graphqlKotlinVersion)
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
    implementation("org.flywaydb:flyway-core:8.3.0")
    implementation("org.flywaydb:flyway-mysql:8.3.0")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.6.3") {
        exclude(group = "com.zaxxer", module = "HikariCP")
    }
    implementation("com.zaxxer:HikariCP:3.4.5")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.6.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
}

flyway {
    url = System.getenv("MYSQL_URL")
    user = System.getenv("MYSQL_USER")
    password = System.getenv("MYSQL_PASSWORD")
}

graphql {
    schema {
        packages = listOf("com.okeicalm.simpleJournalEntry")
    }
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

val graphqlGenerateSDL by tasks.getting(com.expediagroup.graphql.plugin.gradle.tasks.GraphQLGenerateSDLTask::class) {
    packages.set(listOf("com.okeicalm.simpleJournalEntry"))
    schemaFile.set(file("${project.projectDir}/../graphql/schema.graphql"))
}
