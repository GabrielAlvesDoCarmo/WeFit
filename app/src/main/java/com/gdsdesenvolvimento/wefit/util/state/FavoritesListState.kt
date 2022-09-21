package com.gdsdesenvolvimento.wefit.util.state

import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

sealed class FavoritesListState{
    object Loading : FavoritesListState()
    object Empty : FavoritesListState()
    data class Success(val listFavorite: List<InfoRepo>) : FavoritesListState()
    data class Error(val msg: String) : FavoritesListState()
}
