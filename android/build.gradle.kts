plugins {
    id("org.jetbrains.compose") version "1.0.0"
    id("com.android.application")
    kotlin("android")
}

group = "de.lehrbaum.dndtoolsapp"
version = "0.1"

dependencies {
	val ktorVersion = "1.6.7"
    implementation(project(":common"))

    implementation("androidx.activity:activity-compose:1.4.0")

	implementation("io.ktor:ktor-client-core:$ktorVersion")
	implementation("io.ktor:ktor-client-android:$ktorVersion")
	implementation("io.ktor:ktor-client-serialization:$ktorVersion")

	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "de.lehrbaum.dndtoolsapp.android"
        minSdk = 30
        targetSdk = 32
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
}