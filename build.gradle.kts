import org.gradle.jvm.tasks.Jar

plugins {
    idea
    application
    id("java")
}

group = "software.ethan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:5.0.0-beta.24")
    implementation("commons-cli:commons-cli:1.8.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("software.ethan.Main")
}

val fatJar = tasks.register("fatJar", Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "Mr. Bot"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "software.ethan.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    destinationDirectory.set(layout.buildDirectory.dir("dist"))
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}