import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.lehrbaum.dndtoolsapp.common.App
import de.lehrbaum.dndtoolsapp.common.network.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

fun main() {
	val httpClient = HttpClient(CIO) {
		install(JsonFeature) {
			serializer = KotlinxSerializer(Json {
				isLenient = true
				ignoreUnknownKeys = true
				explicitNulls = false
				encodeDefaults = false
			})
		}
	}

	val networkClient = NetworkClient(httpClient)

	runBlocking {
		networkClient.spells.collect {
			println(it)
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