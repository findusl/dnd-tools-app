package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BaseAttribute {
	@SerialName("strength")
	STRENGTH,

	@SerialName("dexterity")
	DEXTERITY,

	@SerialName("constitution")
	CONSTITUTION,

	@SerialName("intelligence")
	INTELLIGENCE,

	@SerialName("wisdom")
	WISDOM,

	@SerialName("charisma")
	CHARISMA
}