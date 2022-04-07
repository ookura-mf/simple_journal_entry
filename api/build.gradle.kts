import com.expediagroup.graphql.plugin.gradle.graphql
import org.gradle.kotlin.dsl.flyway
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.expediagroup.graphql") version "5.1.0"
    id("nu.studer.jooq") version "6.0.1"
    id("org.flywaydb.flyway") version "8.0.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "com.okeicalm"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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

extra["kotlin-coroutines.version"] = "1.6.0"

val graphqlKotlinVersion = "5.1.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq:2.6.5")
    implementation("org.jooq:jooq-codegen:3.16.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.expediagroup", "graphql-kotlin-spring-server", graphqlKotlinVersion)
    implementation("com.expediagroup", "graphql-kotlin-schema-generator", graphqlKotlinVersion)
    implementation("org.flywaydb:flyway-core:8.3.0")
    implementation("org.flywaydb:flyway-mysql:8.3.0")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
    jooqGenerator("mysql:mysql-connector-java:8.0.25")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.6.5")
    runtimeOnly("mysql:mysql-connector-java:8.0.25")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.5")
    testImplementation("io.kotest:kotest-runner-junit5:5.1.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
    testImplementation("com.ninja-squad:springmockk:3.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    username = System.getenv("MYSQL_USER")
                    password = System.getenv("MYSQL_PASSWORD")
                    driver = "com.mysql.cj.jdbc.Driver"
                    url = System.getenv("MYSQL_URL")
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "simple_journal_entry_db"
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isDeprecated = false
                    }
                    target.apply {
                        packageName = "com.okeicalm.simpleJournalEntry.infra.db"
                        directory = "${project.rootDir}/src/main/generated"
                    }
                }
            }
        }
    }
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
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val graphqlGenerateSDL by tasks.getting(com.expediagroup.graphql.plugin.gradle.tasks.GraphQLGenerateSDLTask::class) {
    packages.set(listOf("com.okeicalm.simpleJournalEntry"))
    schemaFile.set(file("${project.projectDir}/../graphql/schema.graphql"))
}
