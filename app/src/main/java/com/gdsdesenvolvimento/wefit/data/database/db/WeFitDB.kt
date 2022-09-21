package com.gdsdesenvolvimento.wefit.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gdsdesenvolvimento.wefit.data.database.dao.Converter
import com.gdsdesenvolvimento.wefit.data.database.dao.FavoriteDao
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.util.constants.AppConstants

@Database(entities = [InfoRepo::class], version = AppConstants.VERSION_DB)
@TypeConverters(Converter::class)
abstract class WeFitDB : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}
