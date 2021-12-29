package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Components(
	@SerialName("v")
	val v: Boolean = false,
	@SerialName("s")
	val s: Boolean = false,
	@SerialName("m")
	@Serializable(with = MaterialComponentSerializer::class)
	val m: MaterialComponent? = null,
	@SerialName("r")
	val r: Boolean = false,
)
