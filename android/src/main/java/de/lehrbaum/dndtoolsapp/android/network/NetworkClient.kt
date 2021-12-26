package de.lehrbaum.dndtoolsapp.android.network

import de.lehrbaum.dndtoolsapp.common.model.SpellList
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

object NetworkClient {
	private val httpClient: HttpClient = HttpClient(Android) {
		install(JsonFeature)
	}

	private val spellSources = flow<Map<String, String>> {
		emit(httpClient.request("https://5e.tools/data/spells/index.json"))
	}

	val spells = spellSources
		.mapLatest {
			coroutineScope {
				it.values.map { jsonFileName ->
					async {
						httpClient.request<SpellList>("https://5e.tools/data/spells/$jsonFileName")
					}
				}.awaitAll().flatMap(SpellList::spells)
			}
		}.flowOn(Dispatchers.IO)

}