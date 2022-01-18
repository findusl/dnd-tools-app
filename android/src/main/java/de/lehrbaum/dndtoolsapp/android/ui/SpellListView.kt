package de.lehrbaum.dndtoolsapp.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.lehrbaum.dndtoolsapp.common.model.Spell
import de.lehrbaum.dndtoolsapp.common.model.loadSampleSpells
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MainView(mainViewModel: MainViewModel) {
	val configuration = LocalConfiguration.current

	// the nested scrollable currently breaks it
	Column(/*modifier = Modifier.verticalScroll(rememberScrollState())*/) {
		Box(modifier = Modifier.weight(1.5f)) {
			SpellList(mainViewModel)
		}
		SpellDetail(mainViewModel)
	}
}

@Composable
private fun SpellList(mainViewModel: MainViewModel) {
	val spells by mainViewModel.allSpells.collectAsState(Dispatchers.Main)
	LazyColumn {
		if (spells.isNotEmpty()) {
			items(spells) { spell ->
				SpellListItem(spell, mainViewModel)
			}
		} else {
			item {
				Text("Still loading please wait.", modifier = Modifier.padding(8.dp))
			}
		}
	}
}

@Composable
private fun LazyItemScope.SpellListItem(
	spell: Spell,
	mainViewModel: MainViewModel
) {
	Text(
		text = spell.name,
		modifier = Modifier
			.clickable { mainViewModel.onSpellClicked(spell) }
			.padding(horizontal = 8.dp, vertical = 4.dp)
			.fillParentMaxWidth()
	)
}

@Composable
private fun ColumnScope.SpellDetail(mainViewModel: MainViewModel) {
	val selectedSpell = mainViewModel.selectedSpell.collectAsState().value
	if (selectedSpell != null) {
		Column(
			modifier = Modifier
				.weight(1f)
				.fillMaxWidth()
				.padding(8.dp)
		) {
			Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
				Text(selectedSpell.name)
				Text(
					"Level ${selectedSpell.level}",
					modifier = Modifier.weight(1f),
					textAlign = TextAlign.End
				)
				IconButton(
					onClick = { mainViewModel.deselectSpell() },
					modifier = Modifier.size(24.dp)
				) {
					Icon(
						Icons.Filled.Close,
						"Close selected Spell",
						tint = Color.Black)
				}
			}
			Text(selectedSpell.school.name, fontSize = 10.sp)
		}
	}
}

@Preview(name = "Main View Preview", widthDp = 300, heightDp = 500)
@Composable
fun Preview() {
	val spells = loadSampleSpells()
	MainView(mainViewModel = MockMainViewModel(spells, spells.random()))
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

	override fun deselectSpell() {
		selectedSpell.value = null
	}
}
