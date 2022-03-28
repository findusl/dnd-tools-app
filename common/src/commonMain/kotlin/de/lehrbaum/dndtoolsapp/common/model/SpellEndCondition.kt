package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class SpellEndCondition(override val shortDescription: String): Describable {
	@SerialName("dispel")
	DISPEL("dispelled"),

	@SerialName("trigger")
	TRIGGER("triggered"),
}