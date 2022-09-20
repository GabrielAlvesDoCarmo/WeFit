package com.gdsdesenvolvimento.wefit.ui.view.base

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseWithViewModelActivity<VB : ViewBinding, VM : ViewModel> : BaseActivity<VB>() {
    private var _viewModel: VM? = null
    protected val viewModel get() = _viewModel!!
    override fun code() {
        _viewModel = activityViewModel()
        codeInjection()
    }

    abstract fun activityViewModel(): VM
    abstract fun codeInjection()

}