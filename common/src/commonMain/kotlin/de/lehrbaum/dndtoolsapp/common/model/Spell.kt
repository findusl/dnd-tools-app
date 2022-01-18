package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
	@SerialName("abilityCheck")
	val abilityCheck: List<BaseAttribute> = listOf(),
	@SerialName("affectsCreatureType")
	val affectsCreatureType: List<String> = listOf(),
	@SerialName("areaTags")
	val areaTags: List<AreaTag> = listOf(),
	val basicRules: Boolean = false,
	@SerialName("damageImmune")
	val damageImmune: List<DamageType> = listOf(),
	@SerialName("damageInflict")
	val damageInflict: List<DamageType> = listOf(),
	@SerialName("damageResist")
	val damageResist: List<DamageType> = listOf(),
	@SerialName("damageVulnerable")
	val damageVulnerable: List<DamageType> = listOf(),
	@SerialName("backgrounds")
	val backgrounds: List<Background> = listOf(),
	@SerialName("time")
	val castingTime: List<Time> = listOf(),
	@SerialName("conditionImmune")
	val conditionImmune: List<String> = listOf(),
	@SerialName("classes")
	val classes: FromClasses = FromClasses(),
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
	val hasFluff: Boolean = false,
	val hasFluffImages: Boolean = false,
	@SerialName("level")
	val level: Int,
	@SerialName("meta")
	val meta: Map<String, Boolean> = mapOf(),
	@SerialName("miscTags")
	val miscTags: List<String> = listOf(),
	@SerialName("name")
	val name: String,
	@SerialName("races")
	val races: List<Race> = listOf(),
	// @Transient val ritual: Boolean = meta["ritual"] ?: false,
	@SerialName("range")
	val range: Range = Range(),
	@SerialName("savingThrow")
	val savingThrow: List<BaseAttribute> = listOf(),
	@Serializable(with = ScalingLevelDiceListSerializer::class)
	val scalingLevelDice: List<ScalingLevelDice> = listOf(),
	@SerialName("school")
	val school: MagicSchool,
	@SerialName("spellAttack")
	val spellAttack: List<String> = listOf(),
	val srd: String = "false",
	@SerialName("source")
	val source: String,
	@SerialName("page")
	val sourcePage: Int,
	val otherSources: List<Source> = listOf(),
	val additionalSources: List<Source> = listOf(),
)
