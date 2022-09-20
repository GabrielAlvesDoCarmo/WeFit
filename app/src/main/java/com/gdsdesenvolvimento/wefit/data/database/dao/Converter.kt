package com.gdsdesenvolvimento.wefit.data.database.dao

import androidx.room.TypeConverter
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromSource(infoRepo: InfoRepo): String = Gson().toJson(infoRepo)
    @TypeConverter
    fun toSource(infoRepo: String): InfoRepo = Gson().fromJson(infoRepo,InfoRepo::class.java)
}