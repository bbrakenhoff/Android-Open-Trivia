package com.bbrakenhoff.opentrivia.ui.difficulty

import android.R
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Test

class TriviaQuestionDifficultyAdapterTest {

    private lateinit var mockParentViewGroup: ViewGroup
    private lateinit var mockLayoutInflater: LayoutInflater

    private lateinit var questionDifficultyAdapter: TriviaQuestionDifficultyAdapter

    @Before
    fun beforeEach() {
        mockParentViewGroup = mockk(relaxed = true)

        mockLayoutInflater = mockk {
            every { inflate(R.layout.simple_list_item_1, mockParentViewGroup, false) } returns mockk<TextView>(relaxed = true)
        }

        mockkStatic("android.view.LayoutInflater")
        every { LayoutInflater.from(any()) } returns mockLayoutInflater

        questionDifficultyAdapter = spyk(TriviaQuestionDifficultyAdapter())
        every { questionDifficultyAdapter.notifyDataSetChanged() } answers { } // Do nothing
    }

    @Test
    fun `getItemCount() returns the size of question difficulties`() {
        assertThat(questionDifficultyAdapter.itemCount).isEqualTo(TriviaQuestionDifficulty.values().size)
    }

    @Test
    fun `onCreateViewHolder(parent, viewType) returns new TriviaCategoryViewHolder with newly inflated view`() {
        val createdViewHolder = questionDifficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        assertThat(createdViewHolder).isInstanceOf(TriviaQuestionDifficultyAdapter.TriviaQuestionDifficultyViewHolder::class.java)
        verify { mockLayoutInflater.inflate(R.layout.simple_list_item_1, any(), false) }
        verify { (createdViewHolder.itemView as TextView).gravity = Gravity.CENTER }
    }

    @Test
    fun `onBindViewHolder(questionDifficultyViewHolder, position) binds TriviaQuestionDifficultyViewHolder to question difficulty at given position`() {
        val createdViewHolder = spyk(questionDifficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0))

        questionDifficultyAdapter.bindViewHolder(createdViewHolder, 0)

        verify { createdViewHolder.bind(TriviaQuestionDifficulty.Any) }
    }

    @Test
    fun `TriviaQuestionDifficultyViewHolder_bind(questionDifficulty) sets the name of the question difficulty to the TextView`() {
        val createdViewHolder = questionDifficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(TriviaQuestionDifficulty.Hard)
        verify { (createdViewHolder.itemView as TextView).text = TriviaQuestionDifficulty.Hard.name }
        val itemTextView = createdViewHolder.itemView as TextView
        verify { itemTextView.setTypeface(itemTextView.typeface, Typeface.NORMAL) }
    }

    @Test
    fun `TriviaQuestionDifficultyViewHolder_bind(questionDifficulty) sets text style to italic when question difficulty represents any question difficulty`() {
        val createdViewHolder = questionDifficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)

        createdViewHolder.bind(TriviaQuestionDifficulty.Any)
        val itemTextView = createdViewHolder.itemView as TextView
        verify { (createdViewHolder.itemView as TextView).text = TriviaQuestionDifficulty.Any.name }
        verify { itemTextView.setTypeface(itemTextView.typeface, Typeface.ITALIC) }
    }

    @Test
    fun `TriviaQuestionDifficultyViewHolder_bind(questionDifficulty) sets click listener`() {
        val createdViewHolder = questionDifficultyAdapter.onCreateViewHolder(mockParentViewGroup, 0)
        val onItemClickListenerMock = mockk<TriviaQuestionDifficultyAdapter.OnItemClickListener>(relaxed = true)
        questionDifficultyAdapter.onItemClickListener = onItemClickListenerMock

        val clickListenerSlot = CapturingSlot<View.OnClickListener>()
        every { createdViewHolder.itemView.setOnClickListener(capture(clickListenerSlot)) } answers { }
        every { createdViewHolder.itemView.performClick() } answers (object : Answer<Boolean> {
            override fun answer(call: Call): Boolean {
                clickListenerSlot.captured.onClick(createdViewHolder.itemView)
                return true
            }
        })

        createdViewHolder.bind(TriviaQuestionDifficulty.Any)
        createdViewHolder.itemView.performClick()

        verify { createdViewHolder.itemView.setOnClickListener(any()) }
        verify { onItemClickListenerMock.onItemClicked(TriviaQuestionDifficulty.Any) }
    }
}
