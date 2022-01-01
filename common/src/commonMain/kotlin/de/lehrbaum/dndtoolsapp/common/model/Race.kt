package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Race(
	val name: String,
	val source: String,
	val baseName: String? = null,
	val baseSource: String? = null,
)
