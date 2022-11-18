plugins {
    kotlin("multiplatform") version "1.7.10"
    id("info.solidsoft.pitest") version "1.9.0"
    application
}

group = "me.isrhernandez"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
    }
}

apply(plugin = "info.solidsoft.pitest")

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                //implementation(pitest('org.pitest:pitest-junit5-plugin:1.0.0'))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:2.0.1")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.0.0-pre.332-kotlin-1.6.21")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.0.0-pre.332-kotlin-1.6.21")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.9.0-pre.332-kotlin-1.6.21")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("me.isrhernandez.application.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

tasks.register<JacocoReport>("applicationCodeCoverageReport") {
    executionData(tasks.run.get())
    sourceSets(sourceSets.main.get())
}

configure<info.solidsoft.gradle.pitest.PitestPluginExtension> {
    pitestVersion.set("1.9.0")
    junit5PluginVersion.set("1.0.0")
    //avoidCallsTo.set(setOf("kotlin.jvm.internal"))
    //mutators.set(setOf("STRONGER"))
    //targetClasses.set(setOf("commontMain.kotlin.*.*"))  //by default "${project.group}.*"
    //targetTests.set(setOf("commontTest.kotlin.*.*Test"))
    targetClasses.set(setOf("*(services|models).*"))  //by default "${project.group}.*"
    targetTests.set(setOf("*.*Test"))

    threads.set(Runtime.getRuntime().availableProcessors())
    outputFormats.set(setOf("XML", "HTML"))

    timestampedReports.set(true)
    //useClasspathFile.set(true)
    //failWhenNoMutations.set(false)
    exportLineCoverage.set(true)
}