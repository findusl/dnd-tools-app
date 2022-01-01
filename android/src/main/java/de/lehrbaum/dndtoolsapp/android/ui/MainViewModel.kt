package de.lehrbaum.dndtoolsapp.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.lehrbaum.dndtoolsapp.common.model.Background
import de.lehrbaum.dndtoolsapp.common.model.Class
import de.lehrbaum.dndtoolsapp.common.model.Race
import de.lehrbaum.dndtoolsapp.common.model.Spell
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json

class MainViewModel : ViewModel() {

	private val ktorHttpClient = HttpClient(Android) {
		install(JsonFeature) {
			serializer = KotlinxSerializer(Json {
				isLenient = true
				ignoreUnknownKeys = true
			})
		}
	}

	private val networkClient = NetworkClient(ktorHttpClient)

	val allSpells = networkClient.spells
		.flowOn(Dispatchers.IO)
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

	private fun List<Spell>.generateFilterOptions() : List<FilterOptions<*>> {
		val sourceOptions = mutableSetOf<String>()
		val levelOptions = mutableSetOf<Int>()
		val classOptions = mutableSetOf<Class>()
		val subClassOptions = mutableSetOf<String>()
		val raceOptions = mutableSetOf<Race>()
		val backgroundOptions = mutableSetOf<Background>()
		val miscOptions = mutableSetOf<String>()
		val schoolOptions = mutableSetOf<String>()
		val damageInflictedOptions = mutableSetOf<String>()
		val conditionInflictedOptions = mutableSetOf<String>()
		val spellAttackOptions = mutableSetOf<String>()
		val savingThrowOptions = mutableSetOf<String>()
		val abilityCheckOptions = mutableSetOf<String>()
		val castTimeOptions = mutableSetOf<String>()
		val durationOptions = mutableSetOf<String>()
		val rangeOptions = mutableSetOf<String>()
		val affectsCreatureTypeOptions = mutableSetOf<String>()

		forEach { spell ->
			sourceOptions.add(spell.source)
			levelOptions.add(spell.level)
			classOptions.addAll(spell.classes.fromClassList)
			//  subClassOptions.addAll(spell.classes.fromClassList)
			raceOptions.addAll(spell.races)
			backgroundOptions.addAll(spell.backgrounds)
			miscOptions.addAll(spell.miscTags)
			schoolOptions.add(spell.school)
			damageInflictedOptions.addAll(spell.damageInflict)
			conditionInflictedOptions.addAll(spell.conditionInflict)
			spellAttackOptions.addAll(spell.spellAttack)
			savingThrowOptions.addAll(spell.savingThrow)
			abilityCheckOptions.addAll(spell.abilityCheck)
			castTimeOptions.addAll(spell.castingTime.map { it.unit })
			affectsCreatureTypeOptions.addAll(spell.affectsCreatureType)
		}

		return listOf(

		)
	}
}

class FilterOptions<OptionType>(
	val category: String,
	val options: Set<OptionType>,
	val criteriaLambda: Spell.(OptionType) -> Boolean
)
