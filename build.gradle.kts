plugins {
    id("java")
    `maven-publish`
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
    paperweight.devBundle("de.verdox.mccreativelab", providers.gradleProperty("version").get())
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
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