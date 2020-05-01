package com.bbrakenhoff.opentrivia.ui.difficulty

import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty

class TriviaQuestionDifficultyAdapter : RecyclerView.Adapter<TriviaQuestionDifficultyAdapter.TriviaQuestionDifficultyViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaQuestionDifficultyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: TextView = layoutInflater.inflate(R.layout.item_question_difficulty, parent, false) as TextView
        return TriviaQuestionDifficultyViewHolder(itemView)
    }

    override fun getItemCount(): Int = TriviaQuestionDifficulty.values().size

    override fun onBindViewHolder(questionDifficultyViewHolder: TriviaQuestionDifficultyViewHolder, position: Int) {
        val questionDifficulty = TriviaQuestionDifficulty.values()[position]
        questionDifficultyViewHolder.bind(questionDifficulty)
    }

    inner class TriviaQuestionDifficultyViewHolder(private val text1: TextView) : RecyclerView.ViewHolder(text1) {

        fun bind(questionDifficulty: TriviaQuestionDifficulty) {
            text1.text = questionDifficulty.name
            text1.setTypeface(text1.typeface, getTypefaceStyle(questionDifficulty))
            text1.setOnClickListener {
                onItemClickListener?.onItemClicked(questionDifficulty)
            }
        }

        private fun getTypefaceStyle(questionDifficulty: TriviaQuestionDifficulty) =
            if (questionDifficulty == TriviaQuestionDifficulty.Any) {
                Typeface.ITALIC
            } else {
                Typeface.NORMAL
            }
    }

    interface OnItemClickListener {
        fun onItemClicked(questionDifficulty: TriviaQuestionDifficulty)
    }
}
