import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.lehrbaum.dndtoolsapp.common.App
import de.lehrbaum.dndtoolsapp.common.initialize
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.stateIn

fun main() {
	initialize()

	val networkClient = NetworkClient()

	runBlocking {
		val allSpells = networkClient.spells.stateIn(GlobalScope)

		launch {
			allSpells.collect {
				println("Managed to load ${it.size} spells. First: ${it.first()}")
			}
		}
		// there should be some better approach but this is easiest right now
		delay(5000)
		cancel()
	}

	// runApplication()
}

private fun runApplication() {
	application {
		Window(onCloseRequest = ::exitApplication) {
			MaterialTheme {
				App()
			}
		}
	}
}