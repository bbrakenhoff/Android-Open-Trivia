package com.bbrakenhoff.opentrivia.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bbrakenhoff.opentrivia.model.TriviaQuestion

@Dao
interface TriviaQuestionDao {

    @Query("SELECT * FROM triviaquestion")
    fun getAll(): LiveData<List<TriviaQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questions: List<TriviaQuestion>)
}
