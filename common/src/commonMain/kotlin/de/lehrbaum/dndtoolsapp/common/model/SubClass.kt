package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubClass(
	@SerialName("class")
	val parentClass: Class,
	@SerialName("subclass")
	val subClass: Class,
)
