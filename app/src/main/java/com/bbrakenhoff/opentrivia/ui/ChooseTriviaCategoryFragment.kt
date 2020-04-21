package com.bbrakenhoff.opentrivia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseTriviaCategoryFragment : Fragment() {

    val chooseTriviaCategoryViewModel: ChooseTriviaCategoryViewModel by viewModel()

    private lateinit var triviaCategoryAdapter: TriviaCategoryAdapter

    private lateinit var triviaCategoryRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        triviaCategoryAdapter = TriviaCategoryAdapter()
        triviaCategoryRecyclerView = view.findViewById(R.id.triviaCategoriesRecyclerView)
        triviaCategoryRecyclerView.adapter = triviaCategoryAdapter

        chooseTriviaCategoryViewModel.categories.observe(viewLifecycleOwner, Observer {
            triviaCategoryAdapter.triviaCategories = it
        })
        chooseTriviaCategoryViewModel.loadCategories()
    }

    companion object {
        fun newInstance() = ChooseTriviaCategoryFragment()
    }
}
