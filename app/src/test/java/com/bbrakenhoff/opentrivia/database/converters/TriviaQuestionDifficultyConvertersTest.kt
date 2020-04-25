package com.bbrakenhoff.opentrivia.database.converters

import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.bbrakenhoff.opentrivia.model.TriviaQuestionType
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class TriviaQuestionDifficultyConvertersTest {

    @Test
    fun `questionDifficultyToString(questionDifficulty) converts string to question difficulty`() {
        assertThat(TriviaQuestionDifficultyConverters.questionDifficultyToString(TriviaQuestionDifficulty.Easy)).isEqualTo(TriviaQuestionDifficulty.Easy.name)
        assertThat(TriviaQuestionDifficultyConverters.questionDifficultyToString(TriviaQuestionDifficulty.Medium)).isEqualTo(TriviaQuestionDifficulty.Medium.name)
        assertThat(TriviaQuestionDifficultyConverters.questionDifficultyToString(TriviaQuestionDifficulty.Hard)).isEqualTo(TriviaQuestionDifficulty.Hard.name)
    }

    @Test
    fun `stringToQuestionDifficulty(questionDifficulty) converts question difficulty to string containing the enum value name`() {
        assertThat(TriviaQuestionDifficultyConverters.stringToQuestionDifficulty(TriviaQuestionDifficulty.Easy.name)).isEqualTo(TriviaQuestionDifficulty.Easy)
        assertThat(TriviaQuestionDifficultyConverters.stringToQuestionDifficulty(TriviaQuestionDifficulty.Medium  .name)).isEqualTo(TriviaQuestionDifficulty.Medium)
        assertThat(TriviaQuestionDifficultyConverters.stringToQuestionDifficulty(TriviaQuestionDifficulty.Hard  .name)).isEqualTo(TriviaQuestionDifficulty.Hard)
    }
}
