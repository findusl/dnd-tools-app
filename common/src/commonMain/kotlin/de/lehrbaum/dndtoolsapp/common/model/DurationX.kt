package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DurationX(
    @SerialName("amount")
    val amount: Int,
    @SerialName("type")
    val type: String
)