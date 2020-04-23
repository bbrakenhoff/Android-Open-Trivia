package com.bbrakenhoff.opentrivia.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bbrakenhoff.opentrivia.model.TriviaCategory

@Dao
interface TriviaCategoryDao {

    @Query("SELECT * FROM triviacategory ORDER BY name")
    fun getAll(): LiveData<List<TriviaCategory>>

    @Query("DELETE FROM triviacategory")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<TriviaCategory>)
}
