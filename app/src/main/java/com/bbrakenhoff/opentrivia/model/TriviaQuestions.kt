package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaQuestions(
    @SerialName("response_code") val responseCode: TriviaResponseCode,
    @SerialName("results") val results: List<TriviaQuestion>
)
