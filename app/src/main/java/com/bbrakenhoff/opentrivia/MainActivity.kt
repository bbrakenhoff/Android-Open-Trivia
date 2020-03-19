package com.bbrakenhoff.opentrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.bbrakenhoff.opentrivia.model.TriviaQuestionType
import com.bbrakenhoff.opentrivia.networking.OpenTriviaApi
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

        testButton.setOnClickListener { _ ->
            CoroutineScope(Dispatchers.IO).launch {
                val result = triviaApi.getQuestions(
                    amount = 5,
                    category = 12,
                    difficulty = TriviaQuestionDifficulty.Hard,
                    type = TriviaQuestionType.Boolean
                )
                Log.d("MainActivity", "Bijoya - onCreate: $result")
            }
        }
    }
}
