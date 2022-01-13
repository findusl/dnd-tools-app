package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AreaTag {
	@SerialName("R")
	CIRCLE,

	@SerialName("N")
	CONE,

	@SerialName("C")
	CUBE,

	@SerialName("Y")
	CYLINDER,

	@SerialName("H")
	HEMISPHERE,

	@SerialName("L")
	LINE,

	@SerialName("MT")
	MULTIPLE_TARGETS,

	@SerialName("ST")
	SINGLE_TARGET,

	@SerialName("S")
	SPHERE,

	@SerialName("Q")
	SQUARE,

	@SerialName("W")
	WALL,
}