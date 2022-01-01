package de.lehrbaum.dndtoolsapp.common

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual fun getPlatformName(): String {
	return "Android"
}

fun initialize() {
	Napier.base(DebugAntilog())
}
