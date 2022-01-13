package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
data class ScalingLevelDice(
	@SerialName("label")
	val label: String,
	@SerialName("scaling")
	val scaling: Map<String, String>
)

internal object ScalingLevelDiceListSerializer : JsonTransformingSerializer<List<ScalingLevelDice>>(ListSerializer(ScalingLevelDice.serializer())) {
	// If response is not an array, then it is a single object that should be wrapped into the array
	override fun transformDeserialize(element: JsonElement): JsonElement =
		if (element !is JsonArray) JsonArray(listOf(element)) else element
}
