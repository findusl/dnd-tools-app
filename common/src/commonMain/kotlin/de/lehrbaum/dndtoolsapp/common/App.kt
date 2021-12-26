package de.lehrbaum.dndtoolsapp.common

import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.lehrbaum.dndtoolsapp.common.model.getPlatformName

@Composable
fun App() {
	var text by remember { mutableStateOf("Hello, World!") }

	Button(onClick = {
		text = "Hello, ${getPlatformName()}"
	}) {
		Text(text)
	}
}
