package com.bbrakenhoff.opentrivia.ui.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.repository.TriviaCategoryRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChooseTriviaCategoryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var categoriesFromRepository: MutableLiveData<List<TriviaCategory>>
    private lateinit var categoryRepositoryMock: TriviaCategoryRepository
    private lateinit var categoriesObserverMock: Observer<List<TriviaCategory>>

    private lateinit var chooseTriviaCategoryViewModel: ChooseTriviaCategoryViewModel

    @Before
    fun beforeEach() {
        categoriesFromRepository = spyk(MutableLiveData(emptyList()))
        categoryRepositoryMock = mockk {
            every { categories } returns categoriesFromRepository
            coEvery { refreshCategories() } answers { categoriesFromRepository.value =
                TestCategories
            }
        }

        chooseTriviaCategoryViewModel = ChooseTriviaCategoryViewModel(categoryRepositoryMock)
        categoriesObserverMock = mockk(relaxed = true)
        chooseTriviaCategoryViewModel.categories.observeForever(categoriesObserverMock)
    }

    @Test
    fun `refreshCategories() refreshes categories in repository and add item for any category in local live data when categories refreshed in repository`() {
        chooseTriviaCategoryViewModel.refreshCategories()

        verify { categoriesObserverMock.onChanged(any()) }
        coVerify { categoryRepositoryMock.refreshCategories() }

        val modifiedCategories = ArrayList<TriviaCategory>(TestCategories)
        modifiedCategories.add(0, TriviaCategory.AnyCategory)
        assertThat(chooseTriviaCategoryViewModel.categories.value).isEqualTo(modifiedCategories)
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
