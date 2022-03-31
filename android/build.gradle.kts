plugins {
    id("org.jetbrains.compose") version "1.1.1"
    id("com.android.application")
    kotlin("android")
}

group = "de.lehrbaum.dndtoolsapp"
version = "0.1"

dependencies {
	val composeVersion = "1.0.5"

	implementation(project(":common"))

	implementation("androidx.compose.runtime:runtime:$composeVersion")
	implementation("androidx.compose.ui:ui:$composeVersion")
	implementation("androidx.compose.foundation:foundation-layout:$composeVersion")
	implementation("androidx.compose.material:material-icons-extended:$composeVersion")
	implementation("androidx.compose.material:material:$composeVersion")
	implementation("androidx.compose.foundation:foundation:$composeVersion")
	implementation("androidx.compose.animation:animation:$composeVersion")
	implementation("androidx.compose.ui:ui-tooling:$composeVersion")
	implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")

	implementation("androidx.activity:activity-compose:1.4.0")

	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "de.lehrbaum.dndtoolsapp.android"
        minSdk = 30
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}

	buildFeatures {
		compose = true
	}
}