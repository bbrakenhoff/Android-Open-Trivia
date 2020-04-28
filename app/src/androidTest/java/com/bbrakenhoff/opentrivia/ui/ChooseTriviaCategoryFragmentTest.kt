package com.bbrakenhoff.opentrivia.ui

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matcher
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class ChooseTriviaCategoryFragmentTest : KoinTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var categoriesMock: MutableLiveData<List<TriviaCategory>>
    private lateinit var chooseCategoryViewModel: ChooseTriviaCategoryViewModel

    @Before
    fun beforeEach() {
        categoriesMock = MutableLiveData(TestCategories)
        chooseCategoryViewModel = mockk(relaxed = true) {
            every { categories } answers { categoriesMock }
        }

        loadKoinModules(module {
            viewModel(override = true) { chooseCategoryViewModel }
        })

        launchFragmentInContainer<ChooseTriviaCategoryFragment>()
    }

    @After
    fun afterEach() {
        stopKoin()
    }

    @Test
    fun displaysLoadedCategoriesInOrder() {
        verify { chooseCategoryViewModel.refreshCategories() }
        onView(withId(R.id.categoriesRecyclerView)).check(withItemCount(equalTo(TestCategories.size)))
    }

    private fun withItemCount(matcher: Matcher<Int>) = object : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter?.itemCount, matcher)
        }
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
