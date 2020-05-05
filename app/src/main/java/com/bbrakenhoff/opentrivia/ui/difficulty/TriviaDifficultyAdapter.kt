package com.bbrakenhoff.opentrivia.ui.difficulty

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaDifficulty

class TriviaDifficultyAdapter : RecyclerView.Adapter<TriviaDifficultyAdapter.TriviaDifficultyViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaDifficultyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: TextView = layoutInflater.inflate(R.layout.item_question_difficulty, parent, false) as TextView
        return TriviaDifficultyViewHolder(itemView)
    }

    override fun getItemCount(): Int = TriviaDifficulty.values().size

    override fun onBindViewHolder(difficultyViewHolder: TriviaDifficultyViewHolder, position: Int) {
        val difficulty = TriviaDifficulty.values()[position]
        difficultyViewHolder.bind(difficulty)
    }

    inner class TriviaDifficultyViewHolder(private val text1: TextView) : RecyclerView.ViewHolder(text1) {

        fun bind(difficulty: TriviaDifficulty) {
            text1.text = difficulty.name
            text1.setTypeface(text1.typeface, getTypefaceStyle(difficulty))
            text1.setOnClickListener {
                onItemClickListener?.onItemClicked(difficulty)
            }
        }

        private fun getTypefaceStyle(difficulty: TriviaDifficulty) =
            if (difficulty == TriviaDifficulty.Any) {
                Typeface.ITALIC
            } else {
                Typeface.NORMAL
            }
    }

    interface OnItemClickListener {
        fun onItemClicked(difficulty: TriviaDifficulty)
    }
}
