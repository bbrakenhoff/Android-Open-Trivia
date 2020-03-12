package com.bbrakenhoff.opentrivia.networking

import com.bbrakenhoff.opentrivia.model.TriviaCategories
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.bbrakenhoff.opentrivia.model.TriviaQuestionType
import com.bbrakenhoff.opentrivia.model.TriviaQuestions
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaApi {

    @GET("/api_category.php")
    fun getCategories(): TriviaCategories

    @GET("/api.php")
    fun getQuestions(
        @Query("amount") amount: Int = 15,
        @Query("category") category: Int? = null,
        @Query("difficulty") difficulty: TriviaQuestionDifficulty? = null,
        @Query("type") type: TriviaQuestionType? = null
    ): TriviaQuestions
}