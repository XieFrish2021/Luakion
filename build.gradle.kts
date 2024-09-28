plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
}

dependencies {
    /* gson */
    implementation("com.google.code.gson:gson:2.11.0")

    /* log4j */
    implementation("org.apache.logging.log4j:log4j-core:2.24.0")
    implementation("org.apache.logging.log4j:log4j-api:2.24.0")

    /* netty */
    implementation("io.netty:netty-all:4.1.113.Final")
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "xyz.luakionmc.luakion.server.main.Main"
        attributes["Multi-Release"] = true
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
}
