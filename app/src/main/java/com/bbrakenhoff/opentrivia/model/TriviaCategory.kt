package com.bbrakenhoff.opentrivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaCategory(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String)
