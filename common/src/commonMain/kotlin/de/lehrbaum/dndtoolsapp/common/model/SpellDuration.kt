package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellDuration(
	@SerialName("duration")
    val durationTime: DurationTime? = null,
	@SerialName("type")
    val type: String,
	@SerialName("concentration")
	val concentration: Boolean = false,
	val ends: List<String> = listOf()
)