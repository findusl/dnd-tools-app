package de.lehrbaum.dndtoolsapp.common

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun getPlatformName(): String {
	return "Android"
}

actual fun getHttpClient() = HttpClient(Android) {
	install(ContentNegotiation) {
		json(Json {
			isLenient = true
		})
	}
}

fun initialize() {
	Napier.base(DebugAntilog())
}
