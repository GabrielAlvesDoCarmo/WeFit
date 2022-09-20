package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gdsdesenvolvimento.wefit.databinding.FragmentFavoritesBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.view.base.BaseFragment
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.FavoriteViewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding,FavoriteViewModel>() {
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater,container,false)
    }

    override fun fragmentViewModel(): FavoriteViewModel {
        return AppInjection.favoriteViewModel(this)
    }

    override fun codeInjectionCreateView() {
        return
    }

    override fun codeInjectionViewCreated() {

    }

}