package com.bbrakenhoff.opentrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bbrakenhoff.opentrivia.ui.category.ChooseTriviaCategoryFragment
import com.bbrakenhoff.opentrivia.ui.difficulty.ChooseTriviaQuestionDifficultyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ChooseTriviaCategoryFragment.newInstance())
            .commit()
    }
}
