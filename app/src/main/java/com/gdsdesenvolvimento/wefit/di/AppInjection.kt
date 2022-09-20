package com.gdsdesenvolvimento.wefit.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.gdsdesenvolvimento.wefit.ui.viewmodel.activity.MainViewModel
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.FavoriteViewModel
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel

object AppInjection {
    fun mainViewModel(owner : ViewModelStoreOwner) : MainViewModel{
        return ViewModelProvider(owner)[MainViewModel::class.java]
    }

    fun favoriteViewModel(owner: ViewModelStoreOwner): FavoriteViewModel {
        return ViewModelProvider(owner)[FavoriteViewModel::class.java]
    }

    fun homeViewModel(owner: ViewModelStoreOwner): HomeViewModel {
        return ViewModelProvider(owner)[HomeViewModel::class.java]
    }
}