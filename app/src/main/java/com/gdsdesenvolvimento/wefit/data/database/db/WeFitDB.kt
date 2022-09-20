package com.gdsdesenvolvimento.wefit.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gdsdesenvolvimento.wefit.data.database.dao.Converter
import com.gdsdesenvolvimento.wefit.data.database.dao.FavoriteDao
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

@Database(entities = [InfoRepo::class], version = 1)
@TypeConverters(Converter::class)
abstract class WeFitDB : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}
