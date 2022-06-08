@file:Suppress("UNUSED_VARIABLE") // I need to name the Source sets for multiplatform magic

import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
	kotlin("plugin.serialization") version "1.6.21"
    id("org.jetbrains.compose") version "1.1.1"
    id("com.android.library")
}

group = "de.lehrbaum.dndtoolsapp"
version = "0.1"

kotlin {
	val kotlinxSerializationVersion = "1.3.3"
	val ktorVersion = "2.0.2"
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

				api("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
				api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
				api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

				api("io.ktor:ktor-client-core:$ktorVersion")
				api("io.ktor:ktor-client-serialization:$ktorVersion")
				api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
				api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

				implementation("io.github.aakira:napier:2.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
				api("androidx.appcompat:appcompat:1.4.2")
				api("androidx.core:core-ktx:1.8.0")

				implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
				api(compose.preview)

				implementation("io.ktor:ktor-client-cio:$ktorVersion")
            }
        }
        val desktopTest by getting
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	// this stuff is also defined in the android source set and I have not found a good way to centralize it.
	compileSdk = 31
	defaultConfig {
		minSdk = 30
		targetSdk = 31
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
}