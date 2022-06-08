@file:Suppress("OPT_IN_USAGE")

package de.lehrbaum.dndtoolsapp.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun getPlatformName(): String {
	return "Desktop"
}

actual fun getHttpClient() = HttpClient(CIO) {
	install(ContentNegotiation) {
		json(Json {
			isLenient = true
			explicitNulls = false
			encodeDefaults = false
		})
	}
}
