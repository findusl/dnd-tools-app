package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellDuration(
	@SerialName("concentration")
	val concentration: Boolean = false,
	val condition: String? = null,
	@SerialName("duration")
	val durationTime: DurationTime? = null,
	val ends: List<String> = listOf(),
	@SerialName("type")
	val type: String,
)