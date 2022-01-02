package de.lehrbaum.dndtoolsapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import de.lehrbaum.dndtoolsapp.android.ui.MainViewModel
import de.lehrbaum.dndtoolsapp.common.initialize
import kotlinx.coroutines.Dispatchers

private val TAG = "MainActivity"

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
	private val mainViewModel: MainViewModel by viewModels()

	init {
		initialize() // TODO should be in application file
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				SimpleApproach(mainViewModel)
			}
		}
	}
}

@ExperimentalMaterialApi
@Composable
private fun BottomSheetApproach(mainViewModel: MainViewModel) {
	val selectedSpell = mainViewModel.selectedSpell.collectAsState().value
	if (selectedSpell != null) {
		val scaffoldState = rememberBottomSheetScaffoldState()
		BottomSheetScaffold(
			scaffoldState = scaffoldState,
			sheetContent = {
				Text(selectedSpell.name)
				Text(selectedSpell.school)
			},
		) {
			SpellList(mainViewModel)
		}
	} else {
		SpellList(mainViewModel)
	}
}

@Composable
private fun SimpleApproach(mainViewModel: MainViewModel) {
	Column {
		Box(modifier = Modifier.weight(1f)) {
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
		Column(modifier = Modifier.weight(1f)) {
			Text(selectedSpell.name)
			Text(selectedSpell.school)
		}
	}
}
