package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DamageType {
	@SerialName("acid")
	ACID,

	@SerialName("bludgeoning")
	BLUDGEONING,

	@SerialName("cold")
	COLD,

	@SerialName("fire")
	FIRE,

	@SerialName("force")
	FORCE,

	@SerialName("lightning")
	LIGHTNING,

	@SerialName("necrotic")
	NECROTIC,

	@SerialName("piercing")
	PIERCING,

	@SerialName("poison")
	POISON,

	@SerialName("psychic")
	PSYCHIC,

	@SerialName("radiant")
	RADIANT,

	@SerialName("slashing")
	SLASHING,

	@SerialName("thunder")
	THUNDER,
}