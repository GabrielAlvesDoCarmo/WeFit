package com.gdsdesenvolvimento.wefit.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding,VM : ViewModel> : Fragment() {
    private var _binding : VB? = null
    protected val binding get() = _binding!!

    private var _viewModel : VM? = null
    protected val viewModel get() = _viewModel!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = fragmentBinding(inflater,container)
        _viewModel = fragmentViewModel()
        codeInjectionCreateView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        codeInjectionViewCreated()
    }

    abstract fun fragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun fragmentViewModel(): VM
    abstract fun codeInjectionCreateView()
    abstract fun codeInjectionViewCreated()

    override fun onDestroy() {
        _binding = null
        _viewModel = null
        super.onDestroy()
    }
}