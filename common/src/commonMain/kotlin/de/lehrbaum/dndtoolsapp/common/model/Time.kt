package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Note, seems very similar to the [DurationTime] class
 */
data class Time(
    @SerialName("number")
    val number: Int,
    @SerialName("unit")
    val unit: String,
	val condition: String? = null
) {
	override fun toString(): String {
		return if (condition != null) "$number $unit, $condition" else "$number $unit"
	}
}