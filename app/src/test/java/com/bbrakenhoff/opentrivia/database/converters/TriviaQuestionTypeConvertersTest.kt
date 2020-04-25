package com.bbrakenhoff.opentrivia.database.converters

import com.bbrakenhoff.opentrivia.model.TriviaQuestionType
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class TriviaQuestionTypeConvertersTest {

    @Test
    fun `questionTypeToString(questionType) converts string to question type`() {
        assertThat(TriviaQuestionTypeConverters.questionTypeToString(TriviaQuestionType.Multiple)).isEqualTo(TriviaQuestionType.Multiple.name)
        assertThat(TriviaQuestionTypeConverters.questionTypeToString(TriviaQuestionType.Boolean)).isEqualTo(TriviaQuestionType.Boolean.name)
    }

    @Test
    fun `stringToQuestionType(questionType) converts question type to string containing the enum value name`() {
        assertThat(TriviaQuestionTypeConverters.stringToQuestionType(TriviaQuestionType.Multiple.name)).isEqualTo(TriviaQuestionType.Multiple)
        assertThat(TriviaQuestionTypeConverters.stringToQuestionType(TriviaQuestionType.Boolean.name)).isEqualTo(TriviaQuestionType.Boolean)
    }
}
