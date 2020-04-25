package com.bbrakenhoff.opentrivia.database.converters

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class IncorrectAnswersConvertersTest {

    @Test
    fun `jsonArrayStringToList(incorrectAnswers) returns list with contents of given JSON array string`() {
        val incorrectAnswers = IncorrectAnswersConverters.jsonArrayStringToList(TestJsonArrayString)
        assertThat(incorrectAnswers).isEqualTo(TestList)
    }

    @Test
    fun `listToJsonArrayString(incorrectAnswers) returns JSON array string with contents of given list`() {
        val incorrectAnswers = IncorrectAnswersConverters.listToJsonArrayString(TestList)
        assertThat(incorrectAnswers).isEqualTo(TestJsonArrayString)
    }

    companion object {
        const val TestJsonArrayString = "[\"Belle\",\"Cinderella\",\"Jasmine\"]"
        val TestList = listOf("Belle", "Cinderella", "Jasmine")
    }
}
