package de.lehrbaum.dndtoolsapp.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.lehrbaum.dndtoolsapp.common.model.Background
import de.lehrbaum.dndtoolsapp.common.model.Class
import de.lehrbaum.dndtoolsapp.common.model.Race
import de.lehrbaum.dndtoolsapp.common.model.Spell
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface MainViewModel {
	val allSpells: StateFlow<List<Spell>>
	val selectedSpell: StateFlow<Spell?>
	fun onSpellClicked(spell: Spell)
}

class MainViewModelImpl : ViewModel(), MainViewModel {

	private val networkClient = NetworkClient()

	override val allSpells = networkClient.spells
		.flowOn(Dispatchers.IO)
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

	private val _selectedSpell = MutableStateFlow<Spell?>(null)
	override val selectedSpell: StateFlow<Spell?> = _selectedSpell.stateIn(viewModelScope, SharingStarted.Eagerly, null)

	override fun onSpellClicked(spell: Spell) {
		_selectedSpell.value = spell
	}

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
