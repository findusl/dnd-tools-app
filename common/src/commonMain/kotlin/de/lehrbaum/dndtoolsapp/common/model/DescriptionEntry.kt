package de.lehrbaum.dndtoolsapp.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
sealed class DescriptionEntry

@Serializable
@SerialName("simple")
data class SimpleEntry(
	val content: String
) : DescriptionEntry() {
	override fun toString(): String {
		return super.toString()
	}
}

@Serializable
@SerialName("entries")
data class SubEntries(
	@SerialName("name")
	val name: String,
	@SerialName("entries")
	val entries: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry>,
) : DescriptionEntry()

@Serializable
@SerialName("quote")
data class QuoteEntries(
	@SerialName("by")
	val by: String,
	@SerialName("entries")
	val entries: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry>,
) : DescriptionEntry()

@Serializable
@SerialName("inset")
data class InsetEntries(
	@SerialName("source")
	val source: String,
	@SerialName("page")
	val page: Int,
	@SerialName("name")
	val name: String,
	@SerialName("entries")
	val entries: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry>,
) : DescriptionEntry()

@Serializable
@SerialName("item")
data class ItemEntries(
	@SerialName("name")
	val name: String,
	@SerialName("entries")
	val entries: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry>,
) : DescriptionEntry()

@Serializable
@SerialName("list")
data class ListEntry(
	val style: String? = null,
	@SerialName("items")
	val items: List<@Serializable(with = DescriptionEntrySerializer::class) DescriptionEntry>,
) : DescriptionEntry() {
	override fun toString(): String {
		return super.toString()
	}
}

@Serializable
@SerialName("table")
data class TableEntry(
	val caption: String? = null,
	val colLabels: List<String>,
	val colStyles: List<String>,
	val rows: List<List<@Serializable(with = TableCellSerializer::class) TableCell>>
) : DescriptionEntry()

internal object DescriptionEntrySerializer : JsonTransformingSerializer<DescriptionEntry>(DescriptionEntry.serializer()) {
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
