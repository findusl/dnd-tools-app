package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Distance(
    @SerialName("type")
    val type: String,
	@SerialName("amount")
	val amount: Int = -1
)