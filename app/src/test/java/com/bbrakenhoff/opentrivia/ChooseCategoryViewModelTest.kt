package com.bbrakenhoff.opentrivia

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.ui.ChooseCategoryViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ChooseCategoryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var chooseCategoryViewModel: ChooseCategoryViewModel

    @Before
    fun beforeEach() {
        chooseCategoryViewModel = ChooseCategoryViewModel()
    }

    @Test
    fun loadCategories_updatesCategoriesFromDb_whenCalled() {
        assertThat(chooseCategoryViewModel.categories.value).isEmpty()

        chooseCategoryViewModel.loadCategories()

        assertThat(chooseCategoryViewModel.categories.value).isEqualTo(
            TestCategories)
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
