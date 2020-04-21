package com.bbrakenhoff.opentrivia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.ui.TriviaCategoryAdapter
import com.bbrakenhoff.opentrivia.ui.TriviaCategoryAdapter.TriviaCategoryViewHolder
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

class TriviaCategoryAdapterTest {

    private lateinit var mockParentViewGroup: ViewGroup
    private lateinit var mockLayoutInflater: LayoutInflater

    private lateinit var triviaCategoryAdapter: TriviaCategoryAdapter

    @Before
    fun beforeEach() {
        mockParentViewGroup = mockk(relaxed = true)

        mockLayoutInflater = mockk {
            every { inflate(android.R.layout.simple_list_item_1, mockParentViewGroup, false) } returns mockk<TextView>(relaxed = true)
        }

        mockkStatic("android.view.LayoutInflater")
        every { LayoutInflater.from(any()) } returns mockLayoutInflater

        triviaCategoryAdapter = spyk(TriviaCategoryAdapter())
        every { triviaCategoryAdapter.notifyDataSetChanged() } answers { } // Do nothing

        triviaCategoryAdapter.triviaCategories = TestTriviaCategories
    }

    @Test
    fun `set triviaCategories calls notifyDataSetChanged()`() {
        verify { triviaCategoryAdapter.notifyDataSetChanged() }
    }

    @Test
    fun `getItemCount() returns the size of triviaCategories`() {
        assertThat(triviaCategoryAdapter.itemCount).isEqualTo(
            TestTriviaCategories.size
        )
    }

    @Test
    fun `onCreateViewHolder(parent, viewType) returns new TriviaCategoryViewHolder with newly inflated view`() {
        val createdViewHolder = triviaCategoryAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        assertThat(createdViewHolder).isInstanceOf(TriviaCategoryViewHolder::class.java)
        verify { mockLayoutInflater.inflate(android.R.layout.simple_list_item_1, any(), false) }
    }

    @Test
    fun `onBindViewHolder(triviaCategoryViewHolder, position) binds TriviaCategoryViewHolder to category at given position`() {
        val position = 3
        val testTriviaCategory = TestTriviaCategories[position]
        val createdViewHolder = spyk(triviaCategoryAdapter.onCreateViewHolder(mockParentViewGroup, 0))

        triviaCategoryAdapter.bindViewHolder(createdViewHolder, position)

        verify { createdViewHolder.bind(testTriviaCategory) }
    }

    @Test
    fun `TriviaCategoryViewHolder_bind(triviaCategory) sets the name of the trivia category to the TextView`() {
        val position = 3
        val testTriviaCategory = TestTriviaCategories[position]
        val createdViewHolder = triviaCategoryAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(testTriviaCategory)
        verify { (createdViewHolder.itemView as TextView).text = testTriviaCategory.name }
    }

    companion object {
        val TestTriviaCategories: List<TriviaCategory> = listOf(
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
