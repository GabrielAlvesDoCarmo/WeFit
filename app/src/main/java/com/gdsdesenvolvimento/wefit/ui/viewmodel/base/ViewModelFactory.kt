package com.gdsdesenvolvimento.wefit.ui.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.wefit.data.repository.WeFitRepository
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.FavoriteViewModel
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel

class ViewModelFactory(
    private val weFitRepository: WeFitRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->HomeViewModel(weFitRepository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java)->FavoriteViewModel(weFitRepository) as T
            else -> throw throw IllegalStateException("ERRO_VIEWMODEL")
        }
    }
}