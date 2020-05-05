package com.bbrakenhoff.opentrivia.ui.difficulty

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaDifficulty
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Test

class TriviaDifficultyAdapterTest {

    private lateinit var mockParentViewGroup: ViewGroup
    private lateinit var mockLayoutInflater: LayoutInflater

    private lateinit var difficultyAdapter: TriviaDifficultyAdapter

    @Before
    fun beforeEach() {
        mockParentViewGroup = mockk(relaxed = true)

        mockLayoutInflater = mockk {
            every { inflate(R.layout.item_question_difficulty, mockParentViewGroup, false) } returns mockk<TextView>(relaxed = true)
        }

        mockkStatic("android.view.LayoutInflater")
        every { LayoutInflater.from(any()) } returns mockLayoutInflater

        difficultyAdapter = spyk(TriviaDifficultyAdapter())
        every { difficultyAdapter.notifyDataSetChanged() } answers { } // Do nothing
    }

    @Test
    fun `getItemCount() returns the size of question difficulties`() {
        assertThat(difficultyAdapter.itemCount).isEqualTo(TriviaDifficulty.values().size)
    }

    @Test
    fun `onCreateViewHolder(parent, viewType) returns new TriviaCategoryViewHolder with newly inflated view`() {
        val createdViewHolder = difficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        assertThat(createdViewHolder).isInstanceOf(TriviaDifficultyAdapter.TriviaDifficultyViewHolder::class.java)
        verify { mockLayoutInflater.inflate(R.layout.item_question_difficulty, any(), false) }
    }

    @Test
    fun `onBindViewHolder(difficultyViewHolder, position) binds TriviaDifficultyViewHolder to question difficulty at given position`() {
        val createdViewHolder = spyk(difficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0))

        difficultyAdapter.bindViewHolder(createdViewHolder, 0)

        verify { createdViewHolder.bind(TriviaDifficulty.Any) }
    }

    @Test
    fun `TriviaDifficultyViewHolder_bind(difficulty) sets the name of the question difficulty to the TextView`() {
        val createdViewHolder = difficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(TriviaDifficulty.Hard)
        verify { (createdViewHolder.itemView as TextView).text = TriviaDifficulty.Hard.name }
        val itemTextView = createdViewHolder.itemView as TextView
        verify { itemTextView.setTypeface(itemTextView.typeface, Typeface.NORMAL) }
    }

    @Test
    fun `TriviaDifficultyViewHolder_bind(difficulty) sets text style to italic when question difficulty represents any question difficulty`() {
        val createdViewHolder = difficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(TriviaDifficulty.Any)
        val itemTextView = createdViewHolder.itemView as TextView
        verify { (createdViewHolder.itemView as TextView).text = TriviaDifficulty.Any.name }
        verify { itemTextView.setTypeface(itemTextView.typeface, Typeface.ITALIC) }
    }

    @Test
    fun `TriviaDifficultyViewHolder_bind(difficulty) sets click listener`() {
        val createdViewHolder = difficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)
        val onItemClickListenerMock = mockk<TriviaDifficultyAdapter.OnItemClickListener>(relaxed = true)
        difficultyAdapter.onItemClickListener = onItemClickListenerMock

        val clickListenerSlot = CapturingSlot<View.OnClickListener>()
        every { createdViewHolder.itemView.setOnClickListener(capture(clickListenerSlot)) } answers { }
        every { createdViewHolder.itemView.performClick() } answers (object : Answer<Boolean> {
            override fun answer(call: Call): Boolean {
                clickListenerSlot.captured.onClick(createdViewHolder.itemView)
                return true
            }
        })

        createdViewHolder.bind(TriviaDifficulty.Any)
        createdViewHolder.itemView.performClick()

        verify { createdViewHolder.itemView.setOnClickListener(any()) }
        verify { onItemClickListenerMock.onItemClicked(TriviaDifficulty.Any) }
    }
}
