package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Duration(
	@SerialName("duration")
    val duration: DurationX,
	@SerialName("type")
    val type: String
)