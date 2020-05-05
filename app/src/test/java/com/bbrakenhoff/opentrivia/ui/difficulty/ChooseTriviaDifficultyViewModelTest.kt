package com.bbrakenhoff.opentrivia.ui.difficulty

import com.bbrakenhoff.opentrivia.model.TriviaDifficulty
import org.junit.Before
import org.junit.Test

class ChooseTriviaDifficultyViewModelTest {

    private lateinit var chooseDifficultyViewModel: ChooseTriviaDifficultyViewModel

    @Before
    fun beforeEach() {
        chooseDifficultyViewModel = ChooseTriviaDifficultyViewModel()
    }

    @Test
    fun `onDifficultyChosen(difficulty) does nothing`() {
        chooseDifficultyViewModel.onDifficultyChosen(TriviaDifficulty.Hard)
    }
}
