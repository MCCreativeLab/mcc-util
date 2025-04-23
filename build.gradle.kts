plugins {
    id("java")
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.16"
    id("xyz.jpenilla.run-paper") version "2.3.1" apply false // Adds runServer and runMojangMappedServer tasks for testing
}

group = "de.verdox.mccreativelab"
description = "mcc-util"

repositories {
    mavenCentral()
    //maven("https://papermc.io/repo/repository/maven-releases/")
    maven("https://repo.verdox.de/snapshots")
}

dependencies {
    compileOnly("de.verdox.mccreativelab.mcc-wrapper:api:" + providers.gradleProperty("version").get())
    compileOnly("de.verdox.mccreativelab:mcc-pack-generator:" + providers.gradleProperty("version").get())
    paperweight.paperDevBundle(providers.gradleProperty("version").get())
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    repositories {
        maven {
            name = "verdox"
            url = uri("https://repo.verdox.de/snapshots")
            credentials {
                username = (findProperty("reposilite.verdox.user") ?: System.getenv("REPO_USER")).toString()
                password = (findProperty("reposilite.verdox.key") ?: System.getenv("REPO_PASSWORD")).toString()
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            pom {
                groupId = "de.verdox.mccreativelab"
                artifactId = "mcc-util"
                version = providers.gradleProperty("version").get()
                from(components["java"])
                licenses {
                    license {
                        name = "GNU GENERAL PUBLIC LICENSE Version 3"
                        url = "https://www.gnu.org/licenses/gpl-3.0.en.html"
                    }
                }
                developers {
                    developer {
                        id = "verdox"
                        name = "Lukas Jonsson"
                        email = "mail.ysp@web.de"
                    }
                }
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}