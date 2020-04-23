package com.bbrakenhoff.opentrivia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbrakenhoff.opentrivia.model.TriviaCategory

@Database(entities = [TriviaCategory::class], version = 1)
abstract class TriviaDatabase : RoomDatabase() {

    abstract fun categoryDao(): TriviaCategoryDao
}
