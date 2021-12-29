package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScalingLevelDice(
	@SerialName("label")
	val label: String,
	@SerialName("scaling")
	val scaling: Map<String, String>
)
