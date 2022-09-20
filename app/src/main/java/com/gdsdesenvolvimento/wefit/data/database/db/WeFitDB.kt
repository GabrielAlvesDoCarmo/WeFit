package com.gdsdesenvolvimento.wefit.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gdsdesenvolvimento.wefit.data.database.dao.Converter
import com.gdsdesenvolvimento.wefit.data.database.dao.FavoriteDao
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

@Database(entities = [InfoRepo::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class WeFitDB : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        const val BANCO_DE_DADOS = "wefit.db"

        @Volatile
        private var instance: WeFitDB? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDataBase(context).also { db ->
                instance = db
            }
        }

        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeFitDB::class.java,
                BANCO_DE_DADOS
            ).build()
    }
}
