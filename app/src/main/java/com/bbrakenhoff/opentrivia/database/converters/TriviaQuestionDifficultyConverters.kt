package com.bbrakenhoff.opentrivia.database.converters

import androidx.room.TypeConverter
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty

object TriviaQuestionDifficultyConverters {

    @JvmStatic
    @TypeConverter
    fun questionDifficultyToString(questionDifficulty: TriviaQuestionDifficulty): String = questionDifficulty.name

    @JvmStatic
    @TypeConverter
    fun stringToQuestionDifficulty(questionDifficulty: String): TriviaQuestionDifficulty = enumValueOf(questionDifficulty)
}
