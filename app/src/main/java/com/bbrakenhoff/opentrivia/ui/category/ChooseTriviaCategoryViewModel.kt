package com.bbrakenhoff.opentrivia.ui.category

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.repository.TriviaCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChooseTriviaCategoryViewModel(private val categoryRepository: TriviaCategoryRepository) : ViewModel() {

    val categories = MediatorLiveData<List<TriviaCategory>>()

    init {
        categories.addSource(categoryRepository.categories) {
            addAnyCategoryToCategories(it)
        }
    }

    private fun addAnyCategoryToCategories(categoriesFromRepository: List<TriviaCategory>) {
        val modifiedCategories = ArrayList<TriviaCategory>()
        modifiedCategories.add(TriviaCategory.AnyCategory)
        modifiedCategories.addAll(categoriesFromRepository)
        categories.value = modifiedCategories
    }

    fun refreshCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.refreshCategories()
        }
    }

    companion object {
        val TestCategories: List<TriviaCategory> = listOf(
            TriviaCategory(9, "General Knowledge"), TriviaCategory(
                10,
                "Entertainment: Books"
            ),
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
