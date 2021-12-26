package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Time(
    @SerialName("number")
    val number: Int,
    @SerialName("unit")
    val unit: String
)