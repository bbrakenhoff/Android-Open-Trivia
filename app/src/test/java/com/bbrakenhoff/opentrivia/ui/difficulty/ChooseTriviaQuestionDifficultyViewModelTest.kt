package com.bbrakenhoff.opentrivia.ui.difficulty

import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import org.junit.Before
import org.junit.Test

class ChooseTriviaQuestionDifficultyViewModelTest {

    private lateinit var chooseQuestionDifficultyViewModel: ChooseTriviaQuestionDifficultyViewModel

    @Before
    fun beforeEach() {
        chooseQuestionDifficultyViewModel = ChooseTriviaQuestionDifficultyViewModel()
    }

    @Test
    fun `onQuestionDifficultyChosen(questionDifficulty) does nothing`() {
        chooseQuestionDifficultyViewModel.onQuestionDifficultyChosen(TriviaQuestionDifficulty.Hard)
    }
}
