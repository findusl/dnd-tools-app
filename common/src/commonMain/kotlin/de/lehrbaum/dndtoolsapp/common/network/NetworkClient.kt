package de.lehrbaum.dndtoolsapp.common.network

import de.lehrbaum.dndtoolsapp.common.getHttpClient
import de.lehrbaum.dndtoolsapp.common.model.SpellList
import io.github.aakira.napier.Napier
import io.ktor.client.request.request
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

private val TAG = "NetworkClient"

class NetworkClient {

	private val httpClient = getHttpClient()

	private val spellSources = flow<Map<String, String>> {
		emit(httpClient.request("https://5e.tools/data/spells/index.json"))
	}

	val spells = spellSources
		.mapLatest { spellSources ->
			coroutineScope {
				val result = spellSources.values.map { jsonFileName ->
					val url = "https://5e.tools/data/spells/$jsonFileName"
					Napier.v({ "Loading spells from $url" }, tag = TAG)
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