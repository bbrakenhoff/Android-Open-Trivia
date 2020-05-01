package com.bbrakenhoff.opentrivia.ui.difficulty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.bbrakenhoff.opentrivia.ui.category.*
import io.mockk.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin

class ChooseTriviaQuestionDifficultyFragmentTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun beforeEach() {
        launchFragmentInContainer<ChooseTriviaQuestionDifficultyFragment>()
    }

    @Test
    fun displaysQuestionDifficultiesInOrder() {
        onView(withId(R.id.questionDifficultiesRecyclerView))
            .check(withItemCount(equalTo(TriviaQuestionDifficulty.values().size)))
    }

    private fun withItemCount(matcher: Matcher<Int>) = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        ViewMatchers.assertThat(adapter?.itemCount, matcher)
    }
}
