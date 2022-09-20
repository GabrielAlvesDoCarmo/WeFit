package com.gdsdesenvolvimento.wefit.data.repository

import com.gdsdesenvolvimento.wefit.data.database.db.WeFitDB
import com.gdsdesenvolvimento.wefit.data.datasource.api.WeFitAPI
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

class WeFitRepository(private val db : WeFitDB,private val api : WeFitAPI) {
    //remote
    suspend fun getRepo(name : String) = api.getRepo(name)
    //local
    suspend fun insert(infoRepo: InfoRepo) = db.getFavoriteDao().insert(infoRepo)
    suspend fun delete(infoRepo: InfoRepo) = db.getFavoriteDao().delete(infoRepo)
    suspend fun getAll() = db.getFavoriteDao().getAll()
}