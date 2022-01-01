package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.Serializable

@Serializable
data class EldritchInvocation(
	val name: String,
	val source: String
)
