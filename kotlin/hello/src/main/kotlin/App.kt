package com.kkirur.kotlin


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}


const val JSON_TEXT = """
    {
        "pico": "CUTE"
    }
"""


@Serializable
data class QandaJsonExampe(
    val pico: String,
    val ddeokGu: String = "Cute"
)

data class QandaExampe(
    val pico: String,
    val ddeokGu: String = "Cute"
)

fun main() {
    println(App().greeting)

    val typeToken = object : TypeToken<QandaExampe>() {}.type
    val objectFromGson = Gson().fromJson<QandaExampe>(JSON_TEXT, typeToken)
    println(objectFromGson)

    val objectFromKotlinxSerializable = Json.decodeFromString<QandaJsonExampe>(JSON_TEXT)
    println(objectFromKotlinxSerializable)
}
