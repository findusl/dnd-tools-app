package de.lehrbaum.dndtoolsapp.common.network

import de.lehrbaum.dndtoolsapp.common.model.SpellList
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.RuntimeException
import kotlin.coroutines.CoroutineContext

private val TAG = "NetworkClient"

class NetworkClient(httpClient: HttpClient) {

	private val spellSources = flow<Map<String, String>> {
		emit(httpClient.request("https://5e.tools/data/spells/index.json"))
	}

	val spells = spellSources
		.mapLatest { spellSources ->
			coroutineScope {
				val result = spellSources.values.map { jsonFileName ->
					val url = "https://5e.tools/data/spells/$jsonFileName"
					println("Loading spells from $url")
					async {
						try {
							httpClient.request<SpellList>(url)
						} catch (e: Exception) {
							Napier.w("Failed to load from $url", e, TAG)
							null
						}
					}
				}.awaitAll()
					.filterNotNull()
					.flatMap(SpellList::spells)
				Napier.i("Finished loading spells")
				result
			}
		}

}