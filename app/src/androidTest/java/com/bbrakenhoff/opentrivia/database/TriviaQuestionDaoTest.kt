package com.bbrakenhoff.opentrivia.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bbrakenhoff.opentrivia.model.TriviaQuestion
import com.bbrakenhoff.opentrivia.model.TriviaQuestionDifficulty
import com.bbrakenhoff.opentrivia.model.TriviaQuestionType
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TriviaQuestionDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TriviaDatabase
    private lateinit var questionDao: TriviaQuestionDao

    private lateinit var questions: LiveData<List<TriviaQuestion>>

    @Before
    fun beforeEach() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, TriviaDatabase::class.java).allowMainThreadQueries().build()
        questionDao = database.questionDao()
        questions = questionDao.getAll()
        questions.observeForever { }
    }

    @After
    fun afterEach() {
        database.close()
    }

    @Test
    fun getAllReturnsEmptyList_whenTableIsEmpty() {
        assertThat(questions.value).isEmpty()
    }

    @Test
    fun getAllReturnsAllQuestions_whenInserted() {
        questionDao.insertAll(TestQuestions)

        assertThat(questions.value).isEqualTo(TestQuestions)
    }

    companion object {

        val TestQuestions = listOf(
            TriviaQuestion(
                category = "Entertainment= Music",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Easy,
                question = "Who wrote the Sinead O`Connor hit &#039;Nothing Compares 2 U&#039;?",
                correctAnswer = "Prince",
                incorrectAnswers = listOf(
                    "Michael Jackson",
                    "Cameo",
                    "Rick James"
                )
            ),
            TriviaQuestion(
                category = "Geography",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "What is the name of the former country that was succeeded by countries such as Serbia, Croatia and Slovenia?",
                correctAnswer = "Yugoslavia",
                incorrectAnswers = listOf(
                    "Czechoslovakia",
                    "Abkhazia",
                    "South Ossetia"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Easy,
                question = "Which of these is a type of monster found in Minecraft?",
                correctAnswer = "Skeleton",
                incorrectAnswers = listOf(
                    "Werewolf",
                    "Vampire",
                    "Minotaur"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "How many Star Spirits do you rescue in the Nintendo 64 video game &quot;Paper Mario&quot;?",
                correctAnswer = "7",
                incorrectAnswers = listOf(
                    "5",
                    "10",
                    "12"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Boolean,
                difficulty = TriviaQuestionDifficulty.Easy,
                question = "In &quot;Super Mario Bros.&quot;, the clouds and bushes have the same artwork and are just different colors.",
                correctAnswer = "True",
                incorrectAnswers = listOf(
                    "False"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "In what Half-Life expansion can you find Gordon&#039;s picture in an &quot;Employee of the Month&quot; picture frame?",
                correctAnswer = "Half-Life= Opposing Force",
                incorrectAnswers = listOf(
                    "Half-Life= Blue Shift",
                    "Half-Life= Decay",
                    "They Hunger"
                )
            ),
            TriviaQuestion(
                category = "Sports",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Hard,
                question = "Which car company is the only Japanese company which won the 24 Hours of Le Mans?",
                correctAnswer = "Mazda",
                incorrectAnswers = listOf(
                    "Toyota",
                    "Subaru",
                    "Nissan"
                )
            ),
            TriviaQuestion(
                category = "History",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Hard,
                question = "The Second Boer War in 1899 was fought where?",
                correctAnswer = "South Africa",
                incorrectAnswers = listOf(
                    "Argentina",
                    "Nepal",
                    "Bulgaria"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Film",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Hard,
                question = "What was the first movie to ever use a Wilhelm Scream?",
                correctAnswer = "Distant Drums",
                incorrectAnswers = listOf(
                    "Treasure of the Sierra Madre",
                    "The Charge at Feather River",
                    "Indiana Jones"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "Which Overwatch character says the line &quot;Heroes never die!&quot;?",
                correctAnswer = "Mercy",
                incorrectAnswers = listOf(
                    "Reaper",
                    "Sonic",
                    "Ana"
                )
            ),
            TriviaQuestion(
                category = "Animals",
                questionType = TriviaQuestionType.Boolean,
                difficulty = TriviaQuestionDifficulty.Easy,
                question = "Cats have whiskers under their legs.",
                correctAnswer = "True",
                incorrectAnswers = listOf(
                    "False"
                )
            ),
            TriviaQuestion(
                category = "Celebrities",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "Which radio personality calls himself &quot;The King of All Media&quot;?",
                correctAnswer = "Howard Stern",
                incorrectAnswers = listOf(
                    "Rush Limbaugh",
                    "Pete Tong",
                    "Ryan Seacrest"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "In the game Pok&eacute;mon Conquest, which warlord is able to bond with Zekrom and a shiny Rayquazza?",
                correctAnswer = "Nobunaga",
                incorrectAnswers = listOf(
                    "The Player",
                    "Oichi",
                    "Hideyoshi"
                )
            ),
            TriviaQuestion(
                category = "General Knowledge",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Hard,
                question = "According to the 2014-2015 Australian Bureau of Statistics, what percentage of Australians were born overseas?",
                correctAnswer = "28%",
                incorrectAnswers = listOf(
                    "13%",
                    "20%",
                    "7%"
                )
            ),
            TriviaQuestion(
                category = "Celebrities",
                questionType = TriviaQuestionType.Boolean,
                difficulty = TriviaQuestionDifficulty.Hard,
                question = "Lady Gaga&#039;s real name is Stefani Joanne Angelina Germanotta.",
                correctAnswer = "True",
                incorrectAnswers = listOf(
                    "False"
                )
            ),
            TriviaQuestion(
                category = "Geography",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "Which of the following is the longest river in Europe?",
                correctAnswer = "Volga",
                incorrectAnswers = listOf(
                    "Danube",
                    "Ural",
                    "Dnieper"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "In the game Tom Clancy&#039;s Rainbow 6 Siege, what organization is Valkyrie from?",
                correctAnswer = "Navy Seals",
                incorrectAnswers = listOf(
                    "S.A.S",
                    "G.I.G.N",
                    "F.B.I"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Music",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "Which of these songs is NOT in The Beatles&#039; album &quot;Sgt. Pepper&#039;s Lonely Hearts Club Band&quot;?",
                correctAnswer = "Strawberry Fields Forever",
                incorrectAnswers = listOf(
                    "Getting Better",
                    "Fixing a Hole",
                    "Lucy in the Sky with Diamonds"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Film",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "What film did James Cameron&#039;s Avatar dethrone as the highest-grossing film ever?",
                correctAnswer = "Titanic",
                incorrectAnswers = listOf(
                    "Star Wars",
                    "Gone with the Wind",
                    "Jaws"
                )
            ),
            TriviaQuestion(
                category = "Animals",
                questionType = TriviaQuestionType.Boolean,
                difficulty = TriviaQuestionDifficulty.Easy,
                question = "Rabbits can see what&#039;s behind themselves without turning their heads.",
                correctAnswer = "True",
                incorrectAnswers = listOf(
                    "False"
                )
            ),
            TriviaQuestion(
                category = "Geography",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "What is the capital of Greenland?",
                correctAnswer = "Nuuk",
                incorrectAnswers = listOf(
                    "Sisimiut",
                    "Narsaq",
                    "Maniitsoq"
                )
            ),
            TriviaQuestion(
                category = "Politics",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Hard,
                question = "What year did the effort to deploy the Common Core State Standards (CCSS) in the US begin?",
                correctAnswer = "2009",
                incorrectAnswers = listOf(
                    "2012",
                    "2006",
                    "1997"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Video Games",
                questionType = TriviaQuestionType.Boolean,
                difficulty = TriviaQuestionDifficulty.Easy,
                question = "There are 2 player roles in Trouble in Terrorist Town.",
                correctAnswer = "False",
                incorrectAnswers = listOf(
                    "True"
                )
            ),
            TriviaQuestion(
                category = "Entertainment= Television",
                questionType = TriviaQuestionType.Multiple,
                difficulty = TriviaQuestionDifficulty.Medium,
                question = "What actor portrays Hogan &quot;Wash&quot; Washburne in the TV Show Firefly?",
                correctAnswer = "Alan Tudyk",
                incorrectAnswers = listOf(
                    "Adam Baldwin",
                    "Nathan Fillion",
                    "Sean Maher"
                )
            )
        )
    }
}
