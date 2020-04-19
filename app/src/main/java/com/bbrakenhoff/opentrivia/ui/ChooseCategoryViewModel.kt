package com.bbrakenhoff.opentrivia.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bbrakenhoff.opentrivia.model.TriviaCategory

class ChooseCategoryViewModel : ViewModel() {

    val categories = MutableLiveData<List<TriviaCategory>>(emptyList())

    fun loadCategories() {
        categories.value = TestCategories
    }

    companion object {
        val TestCategories: List<TriviaCategory> = listOf(
            TriviaCategory(9, "General Knowledge"), TriviaCategory(10,
                                                                   "Entertainment: Books"),
            TriviaCategory(11, "Entertainment: Film"),
            TriviaCategory(12, "Entertainment: Music"),
            TriviaCategory(13, "Entertainment: Musicals & Theatres"),
            TriviaCategory(14, "Entertainment: Television"),
            TriviaCategory(15, "Entertainment: Video Games"),
            TriviaCategory(16, "Entertainment: Board Games"),
            TriviaCategory(17, "Science & Nature"),
            TriviaCategory(18, "Science: Computers"),
            TriviaCategory(19, "Science: Mathematics"),
            TriviaCategory(20, "Mythology"), TriviaCategory(21, "Sports"),
            TriviaCategory(22, "Geography"), TriviaCategory(23, "History"),
            TriviaCategory(24, "Politics"), TriviaCategory(25, "Art"),
            TriviaCategory(26, "Celebrities"), TriviaCategory(27, "Animals"),
            TriviaCategory(28, "Vehicles"),
            TriviaCategory(29, "Entertainment: Comics"),
            TriviaCategory(30, "Science: Gadgets"),
            TriviaCategory(31, "Entertainment: Japanese Anime & Manga"),
            TriviaCategory(32, "Entertainment: Cartoon & Animations")
        )
    }
}
