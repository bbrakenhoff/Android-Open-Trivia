package com.bbrakenhoff.opentrivia.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bbrakenhoff.opentrivia.api.OpenTriviaApi
import com.bbrakenhoff.opentrivia.database.TriviaCategoryDao
import com.bbrakenhoff.opentrivia.model.TriviaCategories
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TriviaCategoryRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var categoriesFromDatabase: ArrayList<TriviaCategory>
    private lateinit var categoriesToInsert: CapturingSlot<ArrayList<TriviaCategory>>
    private lateinit var categoriesFromApi: ArrayList<TriviaCategory>

    private lateinit var categoryDaoMock: TriviaCategoryDao
    private lateinit var triviaApiMock: OpenTriviaApi

    private lateinit var categoryRepository: TriviaCategoryRepository

    @Before
    fun beforeEach() {
        categoriesFromDatabase = ArrayList()
        categoriesToInsert = CapturingSlot()
        categoriesFromApi = ArrayList()

        categoryDaoMock = mockk {
            every { getAll() } returns MutableLiveData(categoriesFromDatabase)
            every { deleteAll() } answers { categoriesFromDatabase.clear() }
            every { insertAll(capture(categoriesToInsert)) } answers { categoriesFromDatabase.addAll(categoriesToInsert.captured) }
        }
        triviaApiMock = mockk {
            coEvery { getCategories() } returns TriviaCategories(categoriesFromApi)
        }

        categoryRepository = TriviaCategoryRepository(triviaApiMock, categoryDaoMock)
        categoryRepository.categories.observeForever {}
    }

    @Test
    fun `refreshCategories() deletes existing categories and saves retrieved categories from API to database`() {
        categoriesFromDatabase.addAll(TestCategories.subList(0, 12))
        categoriesFromApi.addAll(TestCategories.subList(12, TestCategories.lastIndex))

        runBlocking { categoryRepository.refreshCategories() }

        verifyOrder {
            categoryDaoMock.deleteAll()
            runBlocking { triviaApiMock.getCategories() }
            categoryDaoMock.insertAll(categoriesFromApi)
        }

        assertThat(categoryRepository.categories.value).isEqualTo(categoriesFromApi)
    }

    companion object {
        val TestCategories: List<TriviaCategory> = listOf(
            TriviaCategory(9, "General Knowledge"),
            TriviaCategory(10, "Entertainment: Books"),
            TriviaCategory(11, "Entertainment: Film"),
            TriviaCategory(12, "Entertainment: Music"),
            TriviaCategory(13, "Entertainment: Musicals & Theatres"),
            TriviaCategory(14, "Entertainment: Television"),
            TriviaCategory(15, "Entertainment: Video Games"),
            TriviaCategory(16, "Entertainment: Board Games"),
            TriviaCategory(17, "Science & Nature"),
            TriviaCategory(18, "Science: Computers"),
            TriviaCategory(19, "Science: Mathematics"),
            TriviaCategory(20, "Mythology"),
            TriviaCategory(21, "Sports"),
            TriviaCategory(22, "Geography"),
            TriviaCategory(23, "History"),
            TriviaCategory(24, "Politics"),
            TriviaCategory(25, "Art"),
            TriviaCategory(26, "Celebrities"),
            TriviaCategory(27, "Animals"),
            TriviaCategory(28, "Vehicles"),
            TriviaCategory(29, "Entertainment: Comics"),
            TriviaCategory(30, "Science: Gadgets"),
            TriviaCategory(31, "Entertainment: Japanese Anime & Manga"),
            TriviaCategory(32, "Entertainment: Cartoon & Animations")
        )
    }
}
