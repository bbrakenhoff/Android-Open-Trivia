package com.bbrakenhoff.opentrivia.ui.difficulty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaDifficulty
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseTriviaDifficultyFragment : Fragment(), TriviaDifficultyAdapter.OnItemClickListener {

    private lateinit var difficultyRecyclerView: RecyclerView

    private val chooseDifficultyViewModel: ChooseTriviaDifficultyViewModel by viewModel()

    private val difficultyAdapter = TriviaDifficultyAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_choose_difficulty, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        difficultyRecyclerView = view.findViewById(R.id.difficultiesRecyclerView)
        difficultyRecyclerView.adapter = difficultyAdapter
        difficultyAdapter.onItemClickListener = this
    }

    override fun onItemClicked(difficulty: TriviaDifficulty) {
        chooseDifficultyViewModel.onDifficultyChosen(difficulty)
    }

    companion object {
        fun newInstance() = ChooseTriviaDifficultyFragment()
    }
}
