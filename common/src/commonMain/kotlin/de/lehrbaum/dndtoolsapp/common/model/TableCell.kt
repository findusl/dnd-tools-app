package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
sealed class TableCell

@Serializable
@SerialName("simple")
data class SimpleCell(
	val content: String
) : TableCell()

@Serializable
@SerialName("cell")
data class RollCell(
	val roll: RollResult
) : TableCell()

internal object TableCellSerializer : JsonTransformingSerializer<TableCell>(TableCell.serializer()) {
	override fun transformDeserialize(element: JsonElement): JsonElement {
		if (element is JsonPrimitive) {
			return JsonObject(
				mapOf(
					"content" to element,
					"type" to JsonPrimitive("simple")
				)
			)
		}
		return element
	}
}
