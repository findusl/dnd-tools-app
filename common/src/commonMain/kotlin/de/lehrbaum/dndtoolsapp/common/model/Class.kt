package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Class(
	@SerialName("name")
	val name: String,
	@SerialName("source")
	val source: String,
	val definedInSource: String? = null,
	val subSubclass: String? = null,
)