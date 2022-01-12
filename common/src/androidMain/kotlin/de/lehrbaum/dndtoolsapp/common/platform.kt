package de.lehrbaum.dndtoolsapp.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

actual fun getPlatformName(): String {
	return "Android"
}

actual fun getHttpClient() = HttpClient(Android) {
	install(JsonFeature) {
		serializer = KotlinxSerializer(Json {
			isLenient = true
			ignoreUnknownKeys = true
		})
	}
}
