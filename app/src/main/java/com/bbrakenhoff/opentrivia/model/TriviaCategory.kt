package com.bbrakenhoff.opentrivia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class TriviaCategory(
    @PrimaryKey(autoGenerate = false) @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)
