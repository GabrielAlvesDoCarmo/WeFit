package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gdsdesenvolvimento.wefit.databinding.FragmentHomeBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.view.base.BaseFragment
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun fragmentViewModel(): HomeViewModel {
        return AppInjection.homeViewModel(this)
    }

    override fun codeInjectionCreateView() {
        return
    }

    override fun codeInjectionViewCreated() {
        
    }

}