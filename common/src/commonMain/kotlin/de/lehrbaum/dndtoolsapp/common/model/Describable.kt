package de.lehrbaum.dndtoolsapp.common.model

interface Describable {
	val shortDescription: String

	val longDescription: String
		get() = shortDescription
}