package com.bbrakenhoff.opentrivia.ui.difficulty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.ui.category.ChooseTriviaCategoryFragment
import com.bbrakenhoff.opentrivia.ui.category.TriviaCategoryAdapter

class ChooseTriviaQuestionDifficultyFragment : Fragment() {

    private lateinit var questionDifficultyRecyclerView: RecyclerView

    private val questionDifficultyAdapter = TriviaQuestionDifficultyAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_choose_question_difficulty, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        questionDifficultyRecyclerView = view.findViewById(R.id.questionDifficultiesRecyclerView)
        questionDifficultyRecyclerView.adapter = questionDifficultyAdapter
    }

    companion object {
        fun newInstance() = ChooseTriviaQuestionDifficultyFragment()
    }
}
