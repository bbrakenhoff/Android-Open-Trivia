package com.bbrakenhoff.opentrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.bbrakenhoff.opentrivia.model.TriviaQuestionType
import com.bbrakenhoff.opentrivia.networking.OpenTriviaApi
import com.bbrakenhoff.opentrivia.ui.ChooseCategoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val triviaApi: OpenTriviaApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id
            .fragmentContainer, ChooseCategoryFragment.newInstance())
    }
}
