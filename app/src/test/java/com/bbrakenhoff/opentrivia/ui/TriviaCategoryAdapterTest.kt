package com.bbrakenhoff.opentrivia.ui

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.ui.TriviaCategoryAdapter.TriviaCategoryViewHolder
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Test

class TriviaCategoryAdapterTest {

    private lateinit var mockParentViewGroup: ViewGroup
    private lateinit var mockLayoutInflater: LayoutInflater

    private lateinit var categoryAdapter: TriviaCategoryAdapter

    @Before
    fun beforeEach() {
        mockParentViewGroup = mockk(relaxed = true)

        mockLayoutInflater = mockk {
            every { inflate(android.R.layout.simple_list_item_1, mockParentViewGroup, false) } returns mockk<TextView>(relaxed = true)
        }

        mockkStatic("android.view.LayoutInflater")
        every { LayoutInflater.from(any()) } returns mockLayoutInflater

        categoryAdapter = spyk(TriviaCategoryAdapter())
        every { categoryAdapter.notifyDataSetChanged() } answers { } // Do nothing

        categoryAdapter.categories =
            TestCategories
    }

    @Test
    fun `set categories calls notifyDataSetChanged()`() {
        verify { categoryAdapter.notifyDataSetChanged() }
    }

    @Test
    fun `getItemCount() returns the size of categories`() {
        assertThat(categoryAdapter.itemCount).isEqualTo(
            TestCategories.size
        )
    }

    @Test
    fun `onCreateViewHolder(parent, viewType) returns new TriviaCategoryViewHolder with newly inflated view`() {
        val createdViewHolder = categoryAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        assertThat(createdViewHolder).isInstanceOf(TriviaCategoryViewHolder::class.java)
        verify { mockLayoutInflater.inflate(android.R.layout.simple_list_item_1, any(), false) }
    }

    @Test
    fun `onBindViewHolder(triviaCategoryViewHolder, position) binds TriviaCategoryViewHolder to category at given position`() {
        val position = 3
        val testTriviaCategory = TestCategories[position]
        val createdViewHolder = spyk(categoryAdapter.onCreateViewHolder(mockParentViewGroup, 0))

        categoryAdapter.bindViewHolder(createdViewHolder, position)

        verify { createdViewHolder.bind(testTriviaCategory) }
    }

    @Test
    fun `TriviaCategoryViewHolder_bind(category) sets the name of the category to the TextView`() {
        val position = 4
        val testCategory = TestCategories[position]
        val createdViewHolder = categoryAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(testCategory)
        verify { (createdViewHolder.itemView as TextView).text = testCategory.name }
        val itemTextView = createdViewHolder.itemView as TextView
        verify { itemTextView.setTypeface(itemTextView.typeface, Typeface.NORMAL) }
    }

    @Test
    fun `TriviaCategoryViewHolder_bind(category) sets text style to italic when category represents any category`() {
        val position = 0
        val testCategory = TestCategories[position]
        val createdViewHolder = categoryAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(testCategory)
        val itemTextView = createdViewHolder.itemView as TextView
        verify { (createdViewHolder.itemView as TextView).text = testCategory.name }
        verify { itemTextView.setTypeface(itemTextView.typeface, Typeface.ITALIC) }
    }

    @Test
    fun `TriviaCategoryViewHolder_bind(category) sets click listener`() {
        val position = 0
        val testCategory = TestCategories[position]
        val createdViewHolder = categoryAdapter.onCreateViewHolder(mockParentViewGroup, 0)
        val onItemClickListenerMock = mockk<TriviaCategoryAdapter.OnItemClickListener>(relaxed = true)
        categoryAdapter.onItemClickListener = onItemClickListenerMock

        val clickListenerSlot = CapturingSlot<View.OnClickListener>()
        every { createdViewHolder.itemView.setOnClickListener(capture(clickListenerSlot)) } answers { }
        every { createdViewHolder.itemView.performClick() } answers (object : Answer<Boolean> {
            override fun answer(call: Call): Boolean {
                clickListenerSlot.captured.onClick(createdViewHolder.itemView)
                return true
            }
        })

        createdViewHolder.bind(testCategory)
        createdViewHolder.itemView.performClick()

        verify { createdViewHolder.itemView.setOnClickListener(any()) }
        verify { onItemClickListenerMock.onItemClicked(testCategory) }
    }

    companion object {
        val TestCategories: List<TriviaCategory> = listOf(
            TriviaCategory.AnyCategory,
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
