package com.gdsdesenvolvimento.wefit.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(infoRepo: InfoRepo)

    @Query("SELECT * FROM InfoRepo")
    fun getAll(): LiveData<List<InfoRepo>>

    @Delete
    suspend fun delete(infoRepo: InfoRepo)

}