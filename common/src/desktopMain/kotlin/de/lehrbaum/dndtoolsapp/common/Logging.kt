package de.lehrbaum.dndtoolsapp.common

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import java.util.logging.*

fun initialize() {
	val errorHandler = ConsoleHandler().apply {
		level = Level.WARNING

	}
	val normalHandler = NonErrorConsoleHandler()
	Napier.base(DebugAntilog(handler = listOf(errorHandler, normalHandler)))
}

private class NonErrorConsoleHandler(formatter: Formatter = SimpleFormatter()) : StreamHandler(System.out, formatter) {

	init {
		level = Level.ALL
	}

	override fun publish(record: LogRecord?) {
		super.publish(record)
		flush()
	}

	override fun close() {
		flush()
	}

	override fun isLoggable(record: LogRecord?): Boolean {
		// Ignore error level logs
		if (record == null || record.level.intValue() >= Level.WARNING.intValue()) return false
		return super.isLoggable(record)
	}
}
