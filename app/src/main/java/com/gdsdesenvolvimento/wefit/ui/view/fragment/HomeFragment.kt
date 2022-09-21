package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.wefit.data.datasource.retorfit.RetrofitInstance
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.databinding.FragmentHomeBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.view.adapter.HomeAdapter
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.ViewModelFactory
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel
import com.gdsdesenvolvimento.wefit.util.extensions.dialog
import com.gdsdesenvolvimento.wefit.util.extensions.visibility
import com.gdsdesenvolvimento.wefit.util.state.ApiSearchState

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val db by lazy {
        AppInjection.initBd(requireContext())
    }
    var listItems : ArrayList<InfoRepo> = arrayListOf()

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
        observers()
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

    private fun observers() = with(viewModel) {
        searchRepo.observe(viewLifecycleOwner) { apiSearchState ->
            when (apiSearchState) {
                is ApiSearchState.Success -> {
                    binding.progressBar.visibility("i")
                    binding.rvHome.visibility("v")
                    apiSearchState.data.let { response ->
                        HomeAdapter(response)
                    }

                }
                is ApiSearchState.Error -> {
                    binding.progressBar.visibility("i")
                    binding.rvHome.visibility("v")
                    dialog("Error", apiSearchState.msg, true) {
                        return@dialog
                    }
                }
                is ApiSearchState.Loading -> {
                    binding.progressBar.visibility("v")
                    binding.rvHome.visibility("g")

                }
                is ApiSearchState.Empty -> {
                    binding.progressBar.visibility("i")
                    binding.rvHome.visibility("v")
                }
            }
        }
    }
}