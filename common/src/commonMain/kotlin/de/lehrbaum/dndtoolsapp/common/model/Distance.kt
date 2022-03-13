package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Distance(
    @SerialName("type")
    val type: String,
	@SerialName("amount")
	val amount: Int = -1
) {
	override fun toString(): String {
		return if (amount != -1) "$amount $type" else type
	}
}