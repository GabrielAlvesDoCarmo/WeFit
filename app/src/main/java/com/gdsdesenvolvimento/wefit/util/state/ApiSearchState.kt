package com.gdsdesenvolvimento.wefit.util.state

import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi

sealed class ApiSearchState {
    object Loading : ApiSearchState()
    object Empty : ApiSearchState()
    data class Success(val data: ResponseApi) : ApiSearchState()
    data class Error(val msg: String) : ApiSearchState()
}