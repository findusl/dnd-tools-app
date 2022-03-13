package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellDuration(
	@SerialName("concentration")
	val concentration: Boolean = false,
	val condition: String? = null,
	@SerialName("duration")
	val durationTime: DurationTime? = null,
	val ends: List<String> = listOf(),
	@SerialName("type")
	val type: DurationType,
) {
	override fun toString(): String {
		if (type == DurationType.INSTANT) return "Instant"
		val sb = StringBuilder()
		if (concentration) sb.append("Concentration, ")
		sb.append(durationTime.toString())
		return sb.toString()
	}
}