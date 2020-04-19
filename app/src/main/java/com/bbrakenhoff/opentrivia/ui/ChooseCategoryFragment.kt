package com.bbrakenhoff.opentrivia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbrakenhoff.opentrivia.R

class ChooseCategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_choose_category, container)

    companion object {
        fun newInstance() = ChooseCategoryFragment()
    }
}
