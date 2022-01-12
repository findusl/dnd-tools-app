package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
	val source: String,
	// TODO Find the spell where this is null, curious
	val page: Int? = null
)

