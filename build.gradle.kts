plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.7.1"
  id("io.github.goooler.shadow") version "8.1.7"
}

group = "de.leghast"
version = "1.3.4"
description = "Use Item Displays with ease"

java {
  toolchain.languageVersion = JavaLanguageVersion.of(21)
}

repositories {
  maven {
    url = uri("https://repo.codemc.io/repository/maven-snapshots/")
  }
}

dependencies {
  paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
  implementation("org.incendo", "cloud-paper", "2.0.0-beta.8")
  api("net.wesjd:anvilgui:1.9.5-SNAPSHOT")
}

tasks {
  compileJava {
    options.release = 21
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }
  reobfJar {
    outputJar = layout.buildDirectory.file("/home/julius/Paper Development/plugins/Showcase-${project.version}.jar")
  }

  processResources {
    filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    val props = mapOf(
      "name" to project.name,
      "version" to project.version,
      "description" to project.description,
      "apiVersion" to "1.20"
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
      expand(props)
    }
  }

  shadowJar {
    fun reloc(pkg: String) = relocate(pkg, "de.leghast.dependency.$pkg")

    reloc("org.incendo.cloud")
    reloc("io.leangen.geantyref")
  }
}



