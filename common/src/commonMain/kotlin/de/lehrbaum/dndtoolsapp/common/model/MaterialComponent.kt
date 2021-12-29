package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
data class MaterialComponent(
	@SerialName("text")
	val text: String,
	@SerialName("cost")
	val cost: Int = 0,
	@SerialName("consume")
	// can be optional
	val consume: String = "false"
)

internal object MaterialComponentSerializer: JsonTransformingSerializer<MaterialComponent>(MaterialComponent.serializer()) {
	override fun transformDeserialize(element: JsonElement): JsonElement {
		if (element is JsonPrimitive) {
			return JsonObject(mapOf("text" to element))
		}
		return element
	}
}