package de.lehrbaum.dndtoolsapp.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.lehrbaum.dndtoolsapp.common.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MainView(mainViewModel: MainViewModel) {
	// maybe use LocalConfiguration.current for the spelllist height
	// could even specify size depending on selected spell or not

	// the nested scrollable currently breaks it, hopefully works with fixed height
	Column(modifier = Modifier.background(Color.Gray).verticalScroll(rememberScrollState())) {
		val selectedSpell = mainViewModel.selectedSpell.collectAsState().value
		val listHeight = if (selectedSpell != null) 300.dp else 500.dp
		SpellList(mainViewModel, modifier = Modifier.height(listHeight))
		if (selectedSpell != null) {
			SpellDetail(selectedSpell, mainViewModel::deselectSpell)
		}
	}
}

@Composable
private fun SpellList(mainViewModel: MainViewModel, modifier: Modifier = Modifier) {
	val spells by mainViewModel.allSpells.collectAsState(Dispatchers.Main)
	val sortedSpells = spells.sortedWith(compareBy<Spell> { it.level }.thenBy { it.name })

	LazyColumn(modifier = modifier) {
		if (sortedSpells.isNotEmpty()) {
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
			.padding(horizontal = 8.dp, vertical = 2.dp)
			.fillParentMaxWidth(),
		fontSize = 13.sp
	)
}

private val SmallFontSize = 10.sp

@Composable
private fun ColumnScope.SpellDetail(selectedSpell: Spell, deselectSpellAction: () -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp)
	) {
		Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			Text(selectedSpell.name)
			Text(
				"Level ${selectedSpell.level}",
				modifier = Modifier.weight(1f),
				fontSize = SmallFontSize,
				textAlign = TextAlign.End,
			)
			IconButton(
				onClick = deselectSpellAction,
				modifier = Modifier.size(24.dp),
			) {
				Icon(
					Icons.Filled.Close,
					"Close selected Spell",
					tint = Color.Black,
				)
			}
		}

		Row {
			Text(
				selectedSpell.school.name,
				modifier = Modifier.padding(end = 8.dp),
				fontSize = SmallFontSize
			)

			RangeText(selectedSpell, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
		}

		CastingTimeText(selectedSpell.castingTime)

		DurationText(selectedSpell.duration)

		ComponentsText(selectedSpell.components)

		DescriptionBlock(selectedSpell.entries)
	}
}

@Composable
private fun RangeText(spell: Spell, modifier: Modifier = Modifier, textAlign: TextAlign? = null) {
	Text(
		buildAnnotatedString {
			withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
				append("Range: ")
			}
			append(spell.range.distance.toString())
		},
		modifier = modifier,
		textAlign = textAlign,
		fontSize = SmallFontSize,
	)
}

@Composable
private fun CastingTimeText(castingTimes: List<Time>) {
	Text(
		buildAnnotatedString {
			withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
				append("Casting Time: ")
			}
			append(castingTimes.first().toString())
		},
		fontSize = SmallFontSize,
	)
}

@Composable
private fun DurationText(durations: List<SpellDuration>) {
	Text(
		buildAnnotatedString {
			withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
				append("Duration: ")
			}
			append(durations.first().toString())
		},
		fontSize = SmallFontSize,
	)
}

@Composable
private fun ComponentsText(components: Components) {
	Text(
		buildAnnotatedString {
			withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
				append("Components: ")
			}
			append(components.toString())
		},
		fontSize = SmallFontSize,
	)
}

@Suppress("unused") // ColumnScope receiver is unused but ensures this is in Column
@Composable
private fun ColumnScope.DescriptionBlock(entries: List<DescriptionEntry>) {
	entries.forEach { entry ->
		DescriptionEntryView(entry)
	}
}

@Composable
private fun DescriptionEntryView(entry: DescriptionEntry) {
	when (entry) {
		is InsetEntries -> TODO()
		is ItemEntries -> TODO()
		is ListEntry -> DescriptionListEntryView(entry)
		is QuoteEntries -> TODO()
		is SimpleEntry -> Text(entry.content, fontSize = SmallFontSize)
		is SubEntries -> TODO()
		is TableEntry -> TODO()
	}
}

@Composable
private fun DescriptionListEntryView(outerEntry: ListEntry) {
	// TODO check entry.style
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(start = 8.dp)) {
		outerEntry.items.forEach { entry ->
			Row {
				Text(text = "- ", fontSize = SmallFontSize)
				DescriptionEntryView(entry = entry)
			}
		}
	}
}

@Preview(name = "Main View Preview", widthDp = 250, heightDp = 500)
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
