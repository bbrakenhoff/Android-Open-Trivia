package com.bbrakenhoff.opentrivia.database.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bbrakenhoff.opentrivia.model.TriviaQuestionType

object TriviaQuestionTypeConverters {

    @JvmStatic
    @TypeConverter
    fun questionTypeToString(questionType: TriviaQuestionType): String = questionType.name

    @JvmStatic
    @TypeConverter
    fun stringToQuestionType(questionType: String): TriviaQuestionType = enumValueOf(questionType)
}
