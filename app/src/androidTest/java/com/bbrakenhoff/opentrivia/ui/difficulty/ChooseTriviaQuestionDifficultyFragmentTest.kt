package com.bbrakenhoff.opentrivia.ui.difficulty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bbrakenhoff.opentrivia.KoinTestRule
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.ViewActions.withItemCount
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matcher
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ChooseTriviaQuestionDifficultyFragmentTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule(module {
        viewModel(override = true) { chooseQuestionDifficultyViewModel }
    })

    private lateinit var chooseQuestionDifficultyViewModel: ChooseTriviaQuestionDifficultyViewModel

    @Before
    fun beforeEach() {
        chooseQuestionDifficultyViewModel = mockk(relaxed = true)
        launchFragmentInContainer<ChooseTriviaQuestionDifficultyFragment>()
    }

    @Test
    fun displaysQuestionDifficultiesInOrder() {
        onView(withId(R.id.questionDifficultiesRecyclerView))
            .check(withItemCount(equalTo(TriviaQuestionDifficulty.values().size)))
    }

    @Test
    fun callsViewModelWhenItemClicked() {
        onView(withId(R.id.questionDifficultiesRecyclerView))
            .perform(actionOnItemAtPosition<TriviaQuestionDifficultyAdapter.TriviaQuestionDifficultyViewHolder>(0, click()))

        verify { chooseQuestionDifficultyViewModel.onQuestionDifficultyChosen(TriviaQuestionDifficulty.Any) }
    }
}
