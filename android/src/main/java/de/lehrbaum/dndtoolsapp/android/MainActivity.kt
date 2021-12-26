package de.lehrbaum.dndtoolsapp.android

import de.lehrbaum.dndtoolsapp.common.App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import de.lehrbaum.dndtoolsapp.common.model.Spell
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				App()
				val convert = Json.decodeFromString<Spell>(exampleJson)
				Text("Spell name is ${convert.name}")
			}
		}
	}
}

private val exampleJson = """
{
	"name": "Distort Value",
	"source": "AI",
	"page": 75,
	"level": 1,
	"school": "I",
	"time": [
		{
			"number": 1,
			"unit": "minute"
		}
	],
	"range": {
		"type": "point",
		"distance": {
			"type": "touch"
		}
	},
	"components": {
		"v": true
	},
	"duration": [
		{
			"type": "timed",
			"duration": {
				"type": "hour",
				"amount": 8
			}
		}
	],
	"entries": [
		"Do you need to squeeze a few more gold pieces out of a merchant as you try to sell that weird octopus statue you liberated from the chaos temple? Do you need to downplay the worth of some magical assets when the tax collector stops by? Distort value has you covered.",
		"You cast this spell on an object no more than 1 foot on a side, doubling the object's perceived value by adding illusory flourishes or polish to it, or reducing its perceived value by half with the help of illusory scratches, dents, and other unsightly features. Anyone examining the object can ascertain its true value with a successful Intelligence ({@skill Investigation}) check against your spell save DC."
	],
	"entriesHigherLevel": [
		{
			"type": "entries",
			"name": "At Higher Levels",
			"entries": [
				"When you cast this spell using a spell slot of 2nd level or higher, the maximum size of the object increases by 1 foot for each slot level above 1st."
			]
		}
	],
	"classes": {
		"fromClassList": [
			{
				"name": "Bard",
				"source": "PHB"
			},
			{
				"name": "Sorcerer",
				"source": "PHB"
			},
			{
				"name": "Wizard",
				"source": "PHB"
			},
			{
				"name": "Warlock",
				"source": "PHB"
			}
		]
	}
}
""".trimIndent()
