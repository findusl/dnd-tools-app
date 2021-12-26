package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
	@SerialName("classes")
    val classes: Classes,
	@SerialName("components")
    val components: Components,
	@SerialName("duration")
    val duration: List<Duration>,
	@SerialName("entries")
    val entries: List<String>,
	@SerialName("entriesHigherLevel")
    val entriesHigherLevel: List<EntriesHigherLevel>,
	@SerialName("level")
    val level: Int,
	@SerialName("name")
    val name: String,
	@SerialName("page")
    val page: Int,
	@SerialName("range")
    val range: Range,
	@SerialName("school")
    val school: String,
	@SerialName("source")
    val source: String,
	@SerialName("time")
    val time: List<Time>
)