package com.gdsdesenvolvimento.wefit.util.state

import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi

sealed class SearchUserState{
    object Success : SearchUserState()
    data class Error(val msg : String) : SearchUserState()
}
