package com.bbrakenhoff.opentrivia.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.bbrakenhoff.opentrivia.R
import com.bbrakenhoff.opentrivia.ui.category.ChooseTriviaCategoryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer, ChooseTriviaCategoryFragment.newInstance())
//            .commit()

//        findNavController()
//            .navigate(R.id.chooseCategoryFragment)
    }

    private fun findNavController() = findNavController(this, R.id.navHost)
}
