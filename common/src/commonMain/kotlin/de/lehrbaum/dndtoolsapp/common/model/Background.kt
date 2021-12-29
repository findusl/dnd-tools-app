package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Background(
	@SerialName("name")
	val name: String,
	@SerialName("source")
	val source: String
)
