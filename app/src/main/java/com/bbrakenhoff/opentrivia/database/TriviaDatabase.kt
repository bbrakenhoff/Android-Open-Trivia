package com.bbrakenhoff.opentrivia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.bbrakenhoff.opentrivia.model.TriviaQuestion

@Database(entities = [TriviaCategory::class, TriviaQuestion::class], version = 1)
abstract class TriviaDatabase : RoomDatabase() {

    abstract fun categoryDao(): TriviaCategoryDao

    abstract fun questionDao(): TriviaQuestionDao
}
