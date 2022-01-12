package de.lehrbaum.dndtoolsapp.common

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initialize() {
	Napier.base(DebugAntilog())
}
