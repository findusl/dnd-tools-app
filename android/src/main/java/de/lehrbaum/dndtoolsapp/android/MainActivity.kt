package de.lehrbaum.dndtoolsapp.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import de.lehrbaum.dndtoolsapp.android.ui.MainView
import de.lehrbaum.dndtoolsapp.android.ui.MainViewModel
import de.lehrbaum.dndtoolsapp.android.ui.MainViewModelImpl
import de.lehrbaum.dndtoolsapp.common.initialize

private val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
	private val mainViewModel: MainViewModel by viewModels<MainViewModelImpl>()

	init {
		initialize() // TODO should be in application file
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				MainView(mainViewModel)
			}
		}
	}
}
