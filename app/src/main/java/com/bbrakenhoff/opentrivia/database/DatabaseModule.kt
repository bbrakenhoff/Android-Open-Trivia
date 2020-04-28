package com.bbrakenhoff.opentrivia.database

import androidx.room.Room
import com.bbrakenhoff.opentrivia.KoinModule
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

object DatabaseModule : KoinModule {
    override fun start(): Module = module {

        single {
            Room.databaseBuilder(androidContext(), TriviaDatabase::class.java, "trivia.db").build()
        }

        single {
            get<TriviaDatabase>().categoryDao()
        }
    }
}
