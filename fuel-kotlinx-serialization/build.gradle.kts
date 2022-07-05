plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.30"
    `maven-publish`
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        //nodejs()
    }
    ios {
        binaries {
            framework {
                baseName = "Fuel-Serialization"
            }
        }
    }
    explicitApi()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":fuel"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
                api("com.github.kittinunf.result:result:5.2.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.10")
            }
        }
        val jsMain by getting
        val jsTest by getting
        val iosMain by getting
        val iosTest by getting
    }
}
