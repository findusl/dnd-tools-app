package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FromClass(
    @SerialName("name")
    val name: String,
    @SerialName("source")
    val source: String
)