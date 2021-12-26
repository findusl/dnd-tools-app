package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Classes(
    @SerialName("fromClassList")
    val fromClassList: List<FromClass>
)