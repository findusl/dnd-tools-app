package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MagicSchool {
	@SerialName("A")
	ABJURATION,

	@SerialName("C")
	CONJURATION,

	@SerialName("D")
	DIVINATION,

	@SerialName("E")
	ENCHANTMENT,

	@SerialName("V")
	EVOCATION,

	@SerialName("I")
	ILLUSION,

	@SerialName("N")
	NECROMANCY,

	@SerialName("T")
	TRANSMUTATION
}