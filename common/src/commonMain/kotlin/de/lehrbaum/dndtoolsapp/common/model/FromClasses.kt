package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FromClasses(
    @SerialName("fromClassList")
    val fromClassList: List<Class> = listOf()
	// Also has fromClassListvariant
)