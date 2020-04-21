package com.bbrakenhoff.opentrivia.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.model.TriviaCategory

class TriviaCategoryAdapter : RecyclerView.Adapter<TriviaCategoryAdapter.TriviaCategoryViewHolder>() {

    var triviaCategories: List<TriviaCategory> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: TextView = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return TriviaCategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int = triviaCategories.size

    override fun onBindViewHolder(triviaCategoryViewHolder: TriviaCategoryViewHolder, position: Int) {
        val triviaCategory = triviaCategories[position]
        triviaCategoryViewHolder.bind(triviaCategory)
    }

    class TriviaCategoryViewHolder(private val text1: TextView) : RecyclerView.ViewHolder(text1) {

        fun bind(triviaCategory: TriviaCategory) {
            text1.text = triviaCategory.name
        }
    }
}
