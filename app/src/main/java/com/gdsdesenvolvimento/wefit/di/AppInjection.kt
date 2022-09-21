package com.gdsdesenvolvimento.wefit.di

import android.content.Context
import androidx.room.Room
import com.gdsdesenvolvimento.wefit.data.database.db.WeFitDB
import com.gdsdesenvolvimento.wefit.data.datasource.api.WeFitAPI
import com.gdsdesenvolvimento.wefit.data.repository.WeFitRepository
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel

object AppInjection {
    fun initBd(context: Context)= Room.databaseBuilder(context, WeFitDB::class.java, "Wefit.db").build()
    fun getRepository(db: WeFitDB,api : WeFitAPI)= WeFitRepository(db,api)
}