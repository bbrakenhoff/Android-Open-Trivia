package com.bbrakenhoff.opentrivia.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseTriviaCategoryFragment : Fragment(), TriviaCategoryAdapter.OnItemClickListener {

    private val chooseCategoryViewModel: ChooseTriviaCategoryViewModel by viewModel()

    private lateinit var categoryAdapter: TriviaCategoryAdapter

    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_choose_category, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryAdapter = TriviaCategoryAdapter()
        categoryRecyclerView = view.findViewById(R.id.categoriesRecyclerView)
        categoryRecyclerView.adapter = categoryAdapter

        categoryAdapter.onItemClickListener = this

        chooseCategoryViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.categories = it
        })
        chooseCategoryViewModel.refreshCategories()
    }

    override fun onItemClicked(category: TriviaCategory) {
        chooseCategoryViewModel.onCategoryChosen(category)
    }

    companion object {
        fun newInstance() = ChooseTriviaCategoryFragment()
    }
}
