package de.lehrbaum.dndtoolsapp.common.network

import de.lehrbaum.dndtoolsapp.common.model.SpellList
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlin.RuntimeException

class NetworkClient(httpClient: HttpClient) {

	private val spellSources = flow<Map<String, String>> {
		emit(httpClient.request("https://5e.tools/data/spells/index.json"))
	}

	val spells = spellSources
		.mapLatest {
			coroutineScope {
				it.values.map { jsonFileName ->
						val url = "https://5e.tools/data/spells/$jsonFileName"
						println("Loading spells from $url")
						httpClient.request<SpellList>(url)
				}.flatMap(SpellList::spells)
			}
		}.flowOn(Dispatchers.IO)

}