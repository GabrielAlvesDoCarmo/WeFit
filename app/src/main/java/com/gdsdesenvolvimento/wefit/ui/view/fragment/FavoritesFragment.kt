package com.gdsdesenvolvimento.wefit.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsdesenvolvimento.wefit.data.datasource.retorfit.RetrofitInstance
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.data.repository.WeFitRepository
import com.gdsdesenvolvimento.wefit.databinding.FragmentFavoritesBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.view.adapter.FavoriteAdapter
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.ViewModelFactory
import com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment.FavoriteViewModel
import com.gdsdesenvolvimento.wefit.util.extensions.dialog
import com.gdsdesenvolvimento.wefit.util.extensions.visibility
import com.gdsdesenvolvimento.wefit.util.result.RvClickItem
import com.gdsdesenvolvimento.wefit.util.state.FavoritesListState

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoriteViewModel
    private val db by lazy {
        AppInjection.initBd(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        viewModel.getAllFavorites()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun initViewModel(): FavoriteViewModel {
        return ViewModelProvider(
            this, ViewModelFactory(
                AppInjection.getRepository(
                    db,
                    RetrofitInstance.api
                )
            )
        )[FavoriteViewModel::class.java]
    }

    private fun observers() = with(viewModel) {
        favorites.observe(viewLifecycleOwner) { favoriteState ->
            when (favoriteState) {
                is FavoritesListState.Success -> {
                    binding.progressBar.visibility("i")
                    binding.rvHome.visibility("v")
                    initRecycler(favoriteState.listFavorite)
                }
                is FavoritesListState.Error -> {
                    binding.progressBar.visibility("i")
                    binding.rvHome.visibility("v")
                    dialog("Error", favoriteState.msg, true) {
                        return@dialog
                    }
                }
                is FavoritesListState.Empty -> {
                    binding.progressBar.visibility("i")
                    binding.rvHome.visibility("v")
                }
                is FavoritesListState.Loading -> {
                    binding.progressBar.visibility("v")
                    binding.rvHome.visibility("g")
                }
            }
        }
    }

    private fun initRecycler(listFavorite: List<InfoRepo>) = with(binding) {
        rvHome.apply {
            adapter = FavoriteAdapter(listFavorite,this@FavoritesFragment)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}