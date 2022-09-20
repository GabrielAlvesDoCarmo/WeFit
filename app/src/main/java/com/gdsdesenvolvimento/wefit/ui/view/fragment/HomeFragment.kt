package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.gdsdesenvolvimento.wefit.data.database.db.WeFitDB
import com.gdsdesenvolvimento.wefit.data.datasource.retorfit.RetrofitInstance
import com.gdsdesenvolvimento.wefit.databinding.FragmentHomeBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.ViewModelFactory
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val db by lazy {
        AppInjection.initBd(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = initViewModel()
        viewModel.getRepositoryApi()
    }

    private fun initViewModel(): HomeViewModel {
        return ViewModelProvider(
            this,
            ViewModelFactory(
                AppInjection.getRepository(
                    db,
                    RetrofitInstance.api
                )
            )
        )[HomeViewModel::class.java]
    }
}