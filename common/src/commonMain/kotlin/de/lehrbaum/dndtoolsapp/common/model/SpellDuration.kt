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
	val ends: List<SpellEndCondition> = listOf(),
	@SerialName("type")
	val type: DurationType,
) : Describable {
	override val shortDescription: String
		get() {
			return when (type) {
				DurationType.INSTANT -> "Instant"
				DurationType.SPECIAL -> "Special"
				DurationType.PERMANENT -> {
					if (ends.isEmpty()) "permanent"
					else {
						"Until " + ends.joinToString(", ", transform = SpellEndCondition::shortDescription)
					}
				}
				DurationType.TIMED -> {
					val sb = StringBuilder()
					if (concentration) sb.append("Concentration, ")
					sb.append(durationTime.toString())
					sb.toString()
				}
			}
		}
}