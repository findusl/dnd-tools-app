import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.lehrbaum.dndtoolsapp.common.App
import de.lehrbaum.dndtoolsapp.common.initializeLogging
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

fun main() {
	initializeLogging()

	val networkClient = NetworkClient()

	runBlocking {
		val allSpells = networkClient.spells.stateIn(GlobalScope)

		allSpells.collect { spells ->
			println("Managed to load ${spells.size} spells. First: ${spells.first()}")
			cancel()
		}
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