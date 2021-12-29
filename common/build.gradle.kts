import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
	kotlin("plugin.serialization") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0"
    id("com.android.library")
}

group = "de.lehrbaum.dndtoolsapp"
version = "0.1"

kotlin {
	val kotlinxSerializationVersion = "1.3.1"
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
		val ktorVersion = "1.6.7"
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

				api("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
				api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
				api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

				api("io.ktor:ktor-client-core:$ktorVersion")
				api("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.4.0")
                api("androidx.core:core-ktx:1.7.0")
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
            }
        }
        val desktopTest by getting
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	// this stuff is also defined in the android source set and I have not found a good way to centralize it.
	compileSdk = 32
	defaultConfig {
		minSdk = 30
		targetSdk = 32
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
}