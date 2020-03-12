package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaQuestion(
    @SerialName("category") val category: String,
    @SerialName("type") val type: TriviaQuestionType,
    @SerialName("difficulty") val difficulty: TriviaQuestionDifficulty,
    @SerialName("question") val question: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("incorrect_answers") val incorrectAnswers: List<String>
)
