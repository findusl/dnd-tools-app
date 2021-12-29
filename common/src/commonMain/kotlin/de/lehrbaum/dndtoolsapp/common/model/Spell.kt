package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
	@SerialName("classes")
	val classes: FromClasses = FromClasses(),
	@SerialName("backgrounds")
	val backgrounds: List<Background> = listOf(),
	// also has races
	@SerialName("components")
    val components: Components = Components(),
	@SerialName("duration")
    val duration: List<SpellDuration> = listOf(),
	// @SerialName("entries") // Can have weird objects
    // val entries: List<String> = listOf(),
	@SerialName("entriesHigherLevel")
    val entriesHigherLevel: List<EntriesHigherLevel> = listOf(),
	@SerialName("level")
    val level: Int,
	@SerialName("name")
    val name: String,
	@SerialName("range")
    val range: Range,
	@SerialName("school")
    val school: String,
	@SerialName("source")
    val source: String,
	val otherSources: List<Source> = listOf(),
	@SerialName("page")
	val sourcePage: Int,
	@SerialName("time")
    val castingTime: List<Time> = listOf(),
	// They can be an array
	// val scalingLevelDice: ScalingLevelDice? = null,
	val hasFluffImages: Boolean = false,
	@SerialName("spellAttack")
	val spellAttack: List<String> = listOf(),
	@SerialName("areaTags")
	val areaTags: List<String> = listOf(),
	@SerialName("damageInflict")
	val damageInflict: List<String> = listOf(),
	@SerialName("damageResist")
	val damageResist: List<String> = listOf(),
	@SerialName("damageImmune")
	val damageImmune: List<String> = listOf(),
	@SerialName("damageVulnerable")
	val damageVulnerable: List<String> = listOf(),
	@SerialName("savingThrow")
	val savingThrow: List<String> = listOf(),
	@SerialName("abilityCheck")
	val abilityCheck: List<String> = listOf(),
	@SerialName("conditionInflict")
	val conditionInflict: List<String> = listOf(),
	@SerialName("affectsCreatureType")
	val affectsCreatureType: List<String> = listOf(),
	@SerialName("miscTags")
	val miscTags: List<String> = listOf(),
)