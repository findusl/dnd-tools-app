package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntriesHigherLevel(
    @SerialName("entries")
    val entries: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
)