package de.lehrbaum.dndtoolsapp.common

import io.ktor.client.HttpClient

expect fun getPlatformName(): String

expect fun getHttpClient(): HttpClient
