package com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdsdesenvolvimento.wefit.data.repository.WeFitRepository
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.BaseViewModel
import com.gdsdesenvolvimento.wefit.util.state.SearchUserState

class ModalViewModel : BaseViewModel() {
    private var _searchUser = MutableLiveData<SearchUserState>()
    val searchUser: LiveData<SearchUserState> get() = _searchUser
    companion object{
        var newName : String = ""
    }
    fun sendForHome(userName: String) {
        if (userName.isNotEmpty()) {
            newName = userName
            _searchUser.value = SearchUserState.Success
        }else{
            _searchUser.value = SearchUserState.Error("Campo em branco")
        }
    }
}