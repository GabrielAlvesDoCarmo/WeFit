package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.wefit.data.datasource.retorfit.RetrofitInstance
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi
import com.gdsdesenvolvimento.wefit.databinding.FragmentHomeBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.view.adapter.HomeAdapter
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.ViewModelFactory
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.HomeViewModel
import com.gdsdesenvolvimento.wefit.util.extensions.dialog
import com.gdsdesenvolvimento.wefit.util.extensions.visibility
import com.gdsdesenvolvimento.wefit.util.result.RvClickItem
import com.gdsdesenvolvimento.wefit.util.state.ApiSearchState

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val db by lazy {
        AppInjection.initBd(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        viewModel.getRepositoryApi()
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
                        initRecyclerView(response)
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

    private fun initRecyclerView(response: ResponseApi) = with(binding){
        rvHome.apply {
            adapter = HomeAdapter(response,object : RvClickItem{
                override fun clickFavorite(infoRepo: InfoRepo) {
                    viewModel.saveFavoriteInBD(infoRepo)
                }

            },this@HomeFragment)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }
    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}