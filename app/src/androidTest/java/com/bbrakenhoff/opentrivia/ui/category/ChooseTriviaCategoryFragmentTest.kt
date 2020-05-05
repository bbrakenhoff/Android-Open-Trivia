package com.bbrakenhoff.opentrivia.ui.category

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MediatorLiveData
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bbrakenhoff.opentrivia.KoinTestRule
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.ViewActions.withItemCount
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ChooseTriviaCategoryFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule(module {
        viewModel(override = true) { chooseCategoryViewModelMock }
    })

    private lateinit var fragmentScenario: FragmentScenario<ChooseTriviaCategoryFragment>
    private lateinit var navController: TestNavHostController

    private lateinit var categoriesMock: MediatorLiveData<List<TriviaCategory>>
    private lateinit var chooseCategoryViewModelMock: ChooseTriviaCategoryViewModel

    @Before
    fun beforeEach() {
        categoriesMock = MediatorLiveData()
        categoriesMock.value = emptyList()
        chooseCategoryViewModelMock = mockk(relaxed = true) {
            every { categories } answers { categoriesMock }
            every { refreshCategories() } answers {
                categoriesMock.value =
                    TestCategories
            }
        }

        fragmentScenario = launchFragmentInContainer<ChooseTriviaCategoryFragment>()
        val context = ApplicationProvider.getApplicationContext<Context>()
        navController = TestNavHostController(context)
        navController.setGraph(R.navigation.nav_graph)

        fragmentScenario.onFragment {
            setViewNavController(it.requireView(), navController)
        }
    }

    @Test
    fun displaysLoadedCategoriesInOrder() {
        verify { chooseCategoryViewModelMock.refreshCategories() }
        onView(withId(R.id.categoriesRecyclerView)).check(withItemCount(equalTo(TestCategories.size)))
    }

    @Test
    fun callsViewModelWhenItemClicked() {
        onView(withId(R.id.categoriesRecyclerView))
            .perform(actionOnItemAtPosition<TriviaCategoryAdapter.TriviaCategoryViewHolder>(0, click()))

        verify { chooseCategoryViewModelMock.onCategoryChosen(TestCategories[0]) }
    }

    @Test
    fun navigatesToChooseDifficultyFragmentWhenItemClicked() {
        onView(withId(R.id.categoriesRecyclerView))
            .perform(actionOnItemAtPosition<TriviaCategoryAdapter.TriviaCategoryViewHolder>(0, click()))
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.chooseDifficultyFragment)
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
