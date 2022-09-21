package com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdsdesenvolvimento.wefit.data.repository.WeFitRepository
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.BaseViewModel
import com.gdsdesenvolvimento.wefit.util.state.FavoritesListState

class FavoriteViewModel(
    private val weFitRepository: WeFitRepository
) : BaseViewModel() {
    private var _favorites = MutableLiveData<FavoritesListState>()
    val favorites: LiveData<FavoritesListState> get() = _favorites

    init {
        _favorites.value = FavoritesListState.Empty
    }

    fun getAllFavorites() {
        _favorites.value = FavoritesListState.Loading
        launcher {
            try {
                val result = weFitRepository.getAll()
                if (result.isNotEmpty()) {
                    _favorites.value = FavoritesListState.Success(result)
                } else {
                    _favorites.value = FavoritesListState.Empty
                }
            } catch (e: Exception) {
                _favorites.value = FavoritesListState.Error(e.message.toString())
            }
        }
    }
}