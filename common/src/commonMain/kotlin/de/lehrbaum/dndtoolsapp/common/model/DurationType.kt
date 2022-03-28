package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DurationType {
	@SerialName("instant")
	INSTANT,

	@SerialName("timed")
	TIMED,

	@SerialName("permanent")
	PERMANENT,

	@SerialName("special")
	SPECIAL,
}