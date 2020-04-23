package com.bbrakenhoff.opentrivia.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TriviaCategoryDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TriviaDatabase
    private lateinit var categoryDao: TriviaCategoryDao

    private lateinit var categories: LiveData<List<TriviaCategory>>

    @Before
    fun beforeEach() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, TriviaDatabase::class.java).allowMainThreadQueries().build()
        categoryDao = database.categoryDao()
        categories = categoryDao.getAll()
        categories.observeForever { }
    }

    @After
    fun afterEach() {
        database.close()
    }

    @Test
    fun getAllReturnsEmptyList_whenTableIsEmpty() {
        assertThat(categories.value).isEmpty()
    }

    @Test
    fun getAllReturnsAllCategoriesOrderedByName_whenInserted() {
        categoryDao.insertAll(TestCategories)
        assertThat(categories.value).isEqualTo(TestCategories.sortedBy { it.name })
    }

    @Test
    fun getAllReturnsEmptyList_whenAllCategoriesDeleted() {
        categoryDao.insertAll(TestCategories)
        assertThat(categories.value).isEqualTo(TestCategories.sortedBy { it.name })

        categoryDao.deleteAll()
        assertThat(categories.value).isEmpty()
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
