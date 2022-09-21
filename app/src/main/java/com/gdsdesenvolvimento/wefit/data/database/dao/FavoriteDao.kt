package com.gdsdesenvolvimento.wefit.data.database.dao

import androidx.room.*
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(infoRepo: InfoRepo)

    @Query("SELECT * FROM InfoRepo")
    suspend fun getAll(): List<InfoRepo>

    @Delete
    suspend fun delete(infoRepo: InfoRepo)

}