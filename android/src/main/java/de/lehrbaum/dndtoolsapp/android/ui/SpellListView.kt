package de.lehrbaum.dndtoolsapp.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import de.lehrbaum.dndtoolsapp.common.model.Spell
import de.lehrbaum.dndtoolsapp.common.model.loadSampleSpells
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SpellListView(mainViewModel: MainViewModel) {
	val configuration = LocalConfiguration.current

	Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
		Box(modifier = Modifier.weight(2f)) {
			SpellList(mainViewModel)
		}
		SpellDetail(mainViewModel)
	}
}

@Composable
private fun SpellList(mainViewModel: MainViewModel) {
	val spells by mainViewModel.allSpells.collectAsState()
	LazyColumn {
		items(spells) { spell ->
			Text(
				text = spell.name,
				modifier = Modifier
					.clickable { mainViewModel.onSpellClicked(spell) }
					.fillParentMaxWidth()
			)
		}
	}
}

@Composable
private fun ColumnScope.SpellDetail(mainViewModel: MainViewModel) {
	val selectedSpell = mainViewModel.selectedSpell.collectAsState().value
	if (selectedSpell != null) {
		Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
			Text(selectedSpell.name)
			Text(selectedSpell.school)
		}
	}
}

@Preview(name = "Spell List Preview", widthDp = 300, heightDp = 500)
@Composable
fun Preview() {
	val spells = loadSampleSpells()
	SpellListView(mainViewModel = MockMainViewModel(spells, spells.first()))
}

private class MockMainViewModel(
	allSpells: List<Spell>,
	selectedSpell: Spell
) : MainViewModel {
	override val allSpells: MutableStateFlow<List<Spell>> = MutableStateFlow(allSpells)
	override val selectedSpell: MutableStateFlow<Spell?> = MutableStateFlow(selectedSpell)

	override fun onSpellClicked(spell: Spell) {
		selectedSpell.value = spell
	}
}
