package com.bbrakenhoff.opentrivia.repository

import com.bbrakenhoff.opentrivia.TriviaApp
import com.bbrakenhoff.opentrivia.api.OpenTriviaApi
import com.bbrakenhoff.opentrivia.database.TriviaCategoryDao

class TriviaCategoryRepository(private val triviaApi: OpenTriviaApi, private val categoryDao: TriviaCategoryDao) {

    val categories = categoryDao.getAll()

    suspend fun refreshCategories() {
        categoryDao.deleteAll()
        val categories = triviaApi.getCategories()
        categoryDao.insertAll(categories.categories)
    }
}
