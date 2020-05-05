package com.bbrakenhoff.opentrivia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bbrakenhoff.opentrivia.database.converters.IncorrectAnswersConverters
import com.bbrakenhoff.opentrivia.database.converters.TriviaDifficultyConverters
import com.bbrakenhoff.opentrivia.database.converters.TriviaQuestionTypeConverters
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.model.TriviaQuestion

@Database(entities = [TriviaCategory::class, TriviaQuestion::class], version = 1)
@TypeConverters(IncorrectAnswersConverters::class, TriviaQuestionTypeConverters::class, TriviaDifficultyConverters::class)
abstract class TriviaDatabase : RoomDatabase() {

    abstract fun categoryDao(): TriviaCategoryDao

    abstract fun questionDao(): TriviaQuestionDao
}
