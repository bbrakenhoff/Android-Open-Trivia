package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaCategories(
    @SerialName("trivia_categories") val categories: List<TriviaCategory>
)
