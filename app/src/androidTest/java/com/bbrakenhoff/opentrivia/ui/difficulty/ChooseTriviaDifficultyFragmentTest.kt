package com.bbrakenhoff.opentrivia.ui.difficulty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bbrakenhoff.opentrivia.KoinTestRule
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.ViewActions.withItemCount
import com.bbrakenhoff.opentrivia.model.TriviaDifficulty
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ChooseTriviaDifficultyFragmentTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule(module {
        viewModel(override = true) { chooseDifficultyViewModel }
    })

    private lateinit var chooseDifficultyViewModel: ChooseTriviaDifficultyViewModel

    @Before
    fun beforeEach() {
        chooseDifficultyViewModel = mockk(relaxed = true)
        launchFragmentInContainer<ChooseTriviaDifficultyFragment>()
    }

    @Test
    fun displaysDifficultiesInOrder() {
        onView(withId(R.id.difficultiesRecyclerView))
            .check(withItemCount(equalTo(TriviaDifficulty.values().size)))
    }

    @Test
    fun callsViewModelWhenItemClicked() {
        onView(withId(R.id.difficultiesRecyclerView))
            .perform(actionOnItemAtPosition<TriviaDifficultyAdapter.TriviaDifficultyViewHolder>(0, click()))

        verify { chooseDifficultyViewModel.onDifficultyChosen(TriviaDifficulty.Any) }
    }
}
