package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
	@SerialName("abilityCheck")
	val abilityCheck: List<String> = listOf(),
	@SerialName("affectsCreatureType")
	val affectsCreatureType: List<String> = listOf(),
	@SerialName("areaTags")
	val areaTags: List<String> = listOf(),
	@SerialName("damageImmune")
	val damageImmune: List<String> = listOf(),
	@SerialName("damageInflict")
	val damageInflict: List<String> = listOf(),
	@SerialName("damageResist")
	val damageResist: List<String> = listOf(),
	@SerialName("damageVulnerable")
	val damageVulnerable: List<String> = listOf(),
	@SerialName("backgrounds")
	val backgrounds: List<Background> = listOf(),
	@SerialName("time")
	val castingTime: List<Time> = listOf(),
	@SerialName("classes")
	val classes: FromClasses = FromClasses(),
	// also has races
	@SerialName("components")
	val components: Components = Components(),
	@SerialName("conditionInflict")
	val conditionInflict: List<String> = listOf(),
	@SerialName("duration")
	val duration: List<SpellDuration> = listOf(),
	@SerialName("eldritchInvocations")
	val eldritchInvocations: List<EldritchInvocation> = listOf(),
	@SerialName("entries")
	val entries: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry> = listOf(),
	@SerialName("entriesHigherLevel")
	val entriesHigherLevel: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry> = listOf(),
	val hasFluffImages: Boolean = false,
	@SerialName("level")
	val level: Int,
	@SerialName("miscTags")
	val miscTags: List<String> = listOf(),
	@SerialName("name")
	val name: String,
	@SerialName("races")
	val races: List<Race> = listOf(),
	@SerialName("range")
	val range: Range = Range(),
	@SerialName("savingThrow")
	val savingThrow: List<String> = listOf(),
	// They can be an array
	// val scalingLevelDice: ScalingLevelDice? = null,
	@SerialName("spellAttack")
	val spellAttack: List<String> = listOf(),
	@SerialName("school")
	val school: String,
	@SerialName("source")
	val source: String,
	@SerialName("page")
	val sourcePage: Int,
	val otherSources: List<Source> = listOf(),
)
