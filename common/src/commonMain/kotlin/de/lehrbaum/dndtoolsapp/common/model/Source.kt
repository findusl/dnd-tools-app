package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
	val source: String,
	// This is null for Chaos Bolt and Shape water
	val page: Int? = null
)

