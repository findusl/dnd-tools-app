package de.lehrbaum.dndtoolsapp.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.lehrbaum.dndtoolsapp.common.model.*
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus

interface MainViewModel {
	val allSpells: StateFlow<List<Spell>>
	val selectedSpell: StateFlow<Spell?>
	fun onSpellClicked(spell: Spell)
	fun deselectSpell()
}

class MainViewModelImpl : ViewModel(), MainViewModel {

	private val networkClient = NetworkClient()

	override val allSpells = networkClient.spells
		.flowOn(Dispatchers.IO)
		.stateIn(viewModelScope + Dispatchers.Main, SharingStarted.Eagerly, listOf())

	private val _selectedSpell = MutableStateFlow<Spell?>(null)
	override val selectedSpell: StateFlow<Spell?> = _selectedSpell.stateIn(viewModelScope, SharingStarted.Eagerly, null)

	override fun onSpellClicked(spell: Spell) {
		_selectedSpell.value = spell
	}

	override fun deselectSpell() {
		_selectedSpell.value = null
	}

	private fun List<Spell>.generateFilterOptions() : List<FilterOptions<*>> {
		val abilityCheckOptions = BaseAttribute.values()
		val affectsCreatureTypeOptions = mutableSetOf<String>()
		val backgroundOptions = mutableSetOf<Background>()
		val castTimeOptions = mutableSetOf<String>()
		val classOptions = mutableSetOf<Class>()
		val conditionInflictedOptions = mutableSetOf<String>()
		val durationOptions = mutableSetOf<String>()
		val damageInflictedOptions = DamageType.values()
		val levelOptions = mutableSetOf<Int>()
		val miscOptions = mutableSetOf<String>()
		val raceOptions = mutableSetOf<Race>()
		val rangeOptions = mutableSetOf<String>()
		val savingThrowOptions = BaseAttribute.values()
		val schoolOptions = MagicSchool.values()
		val sourceOptions = mutableSetOf<String>()
		val spellAttackOptions = mutableSetOf<String>()
		val subClassOptions = mutableSetOf<String>()

		forEach { spell ->
			sourceOptions.add(spell.source)
			levelOptions.add(spell.level)
			classOptions.addAll(spell.classes.fromClassList)
			//  subClassOptions.addAll(spell.classes.fromClassList)
			raceOptions.addAll(spell.races)
			backgroundOptions.addAll(spell.backgrounds)
			miscOptions.addAll(spell.miscTags)
			conditionInflictedOptions.addAll(spell.conditionInflict)
			spellAttackOptions.addAll(spell.spellAttack)
			castTimeOptions.addAll(spell.castingTime.map { it.unit })
			affectsCreatureTypeOptions.addAll(spell.affectsCreatureType)
		}

		return listOf(

		)
	}
}

class FilterOptions<OptionType>(
	val category: String,
	val options: Collection<OptionType>,
	val criteriaLambda: Spell.(OptionType) -> Boolean
)
