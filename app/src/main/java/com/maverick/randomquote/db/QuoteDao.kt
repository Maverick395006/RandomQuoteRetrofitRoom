package com.maverick.randomquote.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maverick.randomquote.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * From quote")
    suspend fun getQuotes(): List<Result>

}