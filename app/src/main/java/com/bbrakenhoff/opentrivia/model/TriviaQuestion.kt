package com.bbrakenhoff.opentrivia.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bbrakenhoff.opentrivia.database.converters.IncorrectAnswersConverters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class TriviaQuestion(
    @SerialName("category") val category: String,
    @SerialName("type") val questionType: TriviaQuestionType,
    @SerialName("difficulty") val difficulty: TriviaQuestionDifficulty,
    @PrimaryKey(autoGenerate = false) @SerialName("question") val question: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("incorrect_answers") var incorrectAnswers: List<String>
)
