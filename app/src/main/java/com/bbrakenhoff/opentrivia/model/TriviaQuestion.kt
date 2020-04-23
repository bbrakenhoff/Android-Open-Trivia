package com.bbrakenhoff.opentrivia.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class TriviaQuestion(
    @SerialName("category") val category: String,
    @Ignore @SerialName("type") val type: TriviaQuestionType,
    @Ignore @SerialName("difficulty") val difficulty: TriviaQuestionDifficulty,
    @PrimaryKey(autoGenerate = false) @SerialName("question") val question: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @Ignore @SerialName("incorrect_answers") val incorrectAnswers: List<String>
) {
    constructor(category: String, question: String, correctAnswer: String) :
        this(category, TriviaQuestionType.Multiple, TriviaQuestionDifficulty.Medium, question, correctAnswer, emptyList())
}
