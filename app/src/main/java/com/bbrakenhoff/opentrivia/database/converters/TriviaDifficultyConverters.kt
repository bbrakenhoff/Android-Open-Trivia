package com.bbrakenhoff.opentrivia.database.converters

import androidx.room.TypeConverter
import com.bbrakenhoff.opentrivia.model.TriviaDifficulty

object TriviaDifficultyConverters {

    @JvmStatic
    @TypeConverter
    fun difficultyToString(difficulty: TriviaDifficulty): String = difficulty.name

    @JvmStatic
    @TypeConverter
    fun stringToDifficulty(difficulty: String): TriviaDifficulty = enumValueOf(difficulty)
}
