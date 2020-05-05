package com.bbrakenhoff.opentrivia.database.converters

import com.bbrakenhoff.opentrivia.model.TriviaDifficulty
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TriviaDifficultyConvertersTest {

    @Test
    fun `difficultyToString(difficulty) converts string to question difficulty`() {
        assertThat(TriviaDifficultyConverters.difficultyToString(TriviaDifficulty.Easy)).isEqualTo(TriviaDifficulty.Easy.name)
        assertThat(TriviaDifficultyConverters.difficultyToString(TriviaDifficulty.Medium)).isEqualTo(TriviaDifficulty.Medium.name)
        assertThat(TriviaDifficultyConverters.difficultyToString(TriviaDifficulty.Hard)).isEqualTo(TriviaDifficulty.Hard.name)
    }

    @Test
    fun `stringToDifficulty(difficulty) converts question difficulty to string containing the enum value name`() {
        assertThat(TriviaDifficultyConverters.stringToDifficulty(TriviaDifficulty.Easy.name)).isEqualTo(TriviaDifficulty.Easy)
        assertThat(TriviaDifficultyConverters.stringToDifficulty(TriviaDifficulty.Medium  .name)).isEqualTo(TriviaDifficulty.Medium)
        assertThat(TriviaDifficultyConverters.stringToDifficulty(TriviaDifficulty.Hard  .name)).isEqualTo(TriviaDifficulty.Hard)
    }
}
