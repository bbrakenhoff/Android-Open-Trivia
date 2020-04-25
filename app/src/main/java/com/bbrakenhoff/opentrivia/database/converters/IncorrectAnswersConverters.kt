package com.bbrakenhoff.opentrivia.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

object IncorrectAnswersConverters {

    private val json = Json(JsonConfiguration.Stable)

    @JvmStatic
    @TypeConverter
    fun listToJsonArrayString(incorrectAnswers: List<String>): String {
        return json.stringify(String.serializer().list, incorrectAnswers)
    }

    @JvmStatic
    @TypeConverter
    fun jsonArrayStringToList(incorrectAnswers: String): List<String> {
        return json.parse(String.serializer().list, incorrectAnswers)
    }
}
