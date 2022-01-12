package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.Serializable

@Serializable
data class RollResult(
	val exact: Int = -1,
	val max: Int = -1,
	val min: Int = -1,
) {
	init {
		assert(exact > 0 || (max > 0 && min > 0)) {
			"Either exact value or min and max for roll have to be present."
		}
	}
}
