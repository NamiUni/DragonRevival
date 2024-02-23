import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.3"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("io.papermc.paper", "paper-api", "1.20.4-R0.1-SNAPSHOT")
}

paper {
    authors = listOf("Unitarou")
    apiVersion = "1.20"
    foliaSupported = false
    version = "1.0"

    val mainPackage = "com.github.namiuni.dragonrevival"
    main = "$mainPackage.DragonRevival"
    bootstrapper = "$mainPackage.DragonRevivalBootstrap"
}

tasks {
    compileJava {
        this.options.encoding = Charsets.UTF_8.name()
        this.options.release.set(17)
    }

    shadowJar {
        this.archiveClassifier.set(null as String?)
        this.archiveVersion.set(paper.version)
        mergeServiceFiles()
    }

    runServer {
        minecraftVersion("1.20.4")
        downloadPlugins {
            url("https://download.luckperms.net/1530/bukkit/loader/LuckPerms-Bukkit-5.4.117.jar")
        }
    }

    test {
        useJUnitPlatform()
    }
}
