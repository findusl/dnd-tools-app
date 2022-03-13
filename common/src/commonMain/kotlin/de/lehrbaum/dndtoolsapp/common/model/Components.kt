package de.lehrbaum.dndtoolsapp.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Components(
	@SerialName("v")
	val v: Boolean = false,
	@SerialName("s")
	val s: Boolean = false,
	@SerialName("m")
	@Serializable(with = MaterialComponentSerializer::class)
	val m: MaterialComponent? = null,
	@SerialName("r")
	val r: Boolean = false,
) {
	override fun toString(): String {
		val sb = StringBuilder()
		if (v) sb.append("V, ")
		if (s) sb.append("S, ")
		if (r) sb.append("R, ")
		if (m != null) sb.append("M (").append(m.text).append("), ")
		sb.delete(sb.length - 2, sb.length)
		return sb.toString()
	}
}
