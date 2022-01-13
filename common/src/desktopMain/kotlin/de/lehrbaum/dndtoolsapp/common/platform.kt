package de.lehrbaum.dndtoolsapp.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

actual fun getPlatformName(): String {
	return "Desktop"
}

actual fun getHttpClient() = HttpClient(CIO) {
	install(JsonFeature) {
		serializer = KotlinxSerializer(Json {
			isLenient = true
			explicitNulls = false
			encodeDefaults = false
		})
	}
}
