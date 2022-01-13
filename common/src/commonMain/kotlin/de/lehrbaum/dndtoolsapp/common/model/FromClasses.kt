package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FromClasses(
	@SerialName("fromClassList")
	val fromClassList: List<Class> = listOf(),
	val fromSubclass: List<SubClass> = listOf(),
	val fromClassListVariant: List<Class> = listOf(),
)