package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Range(
	@SerialName("distance")
    val distance: Distance? = null,
	// type can be special then distance is null
	@SerialName("type")
	val type: String = ""
)