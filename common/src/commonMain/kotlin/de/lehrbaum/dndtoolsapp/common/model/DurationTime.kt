package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Note, seems very similar to the [Time] class
 */
data class DurationTime(
    @SerialName("amount")
    val amount: Int,
	@SerialName("type")
	val type: String,
	val upTo: Boolean = false,
) {
	override fun toString(): String {
		return "$amount $type"
	}
}