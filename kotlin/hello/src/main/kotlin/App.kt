package com.kkirur.kotlin


import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.stream.JsonReader

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
        

fun main() {
    println(App().greeting)
    
    val typeToken = object : TypeToken<QandaExampe>() {}.type
    val objectFromGson = Gson().fromJson<QandaExampe>(JSON_TEXT, typeToken)
    println(objectFromGson)
}

data class QandaExampe(
    val pico: String,
    val ddeokGu: String = "Cute"
        
)


