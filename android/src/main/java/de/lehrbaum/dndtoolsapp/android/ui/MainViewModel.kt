package de.lehrbaum.dndtoolsapp.android.ui

import androidx.lifecycle.ViewModel
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature

class MainViewModel : ViewModel() {

	private val ktorHttpClient = HttpClient(Android) {
		install(JsonFeature)
	}

	private val networkClient = NetworkClient(ktorHttpClient)
}