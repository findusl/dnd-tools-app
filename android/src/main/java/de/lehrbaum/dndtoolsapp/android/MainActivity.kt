package de.lehrbaum.dndtoolsapp.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import de.lehrbaum.dndtoolsapp.android.ui.MainViewModel
import de.lehrbaum.dndtoolsapp.common.initialize
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
	private val mainViewModel: MainViewModel by viewModels()

	init {
		initialize() // TODO should be in application file
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				List(mainViewModel)
			}
		}
	}
}

@Composable
private fun List(mainViewModel: MainViewModel) {
	val spells by mainViewModel.allSpells.collectAsState(Dispatchers.Main)
	LazyColumn {
		items(spells) { spell ->
			Text(spell.name)
		}
	}
}
