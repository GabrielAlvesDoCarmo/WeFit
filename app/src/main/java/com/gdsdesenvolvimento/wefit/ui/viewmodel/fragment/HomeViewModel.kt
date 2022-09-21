package com.gdsdesenvolvimento.wefit.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo
import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi
import com.gdsdesenvolvimento.wefit.data.repository.WeFitRepository
import com.gdsdesenvolvimento.wefit.ui.viewmodel.base.BaseViewModel
import com.gdsdesenvolvimento.wefit.util.state.ApiSearchState
import retrofit2.Response

class HomeViewModel(
    private val weFitRepository: WeFitRepository
) : BaseViewModel() {
    private var _searchRepo = MutableLiveData<ApiSearchState>()
    val searchRepo: LiveData<ApiSearchState> get() = _searchRepo
    init {
        _searchRepo.value = ApiSearchState.Empty
    }
    fun getRepositoryApi(name: String = "appswefit") {
        _searchRepo.value = ApiSearchState.Loading
        launcher {
            try {
                val response = weFitRepository.getRepo(name)
                verifyResponse(response)
            } catch (e: Exception) {
                e.printStackTrace()
                _searchRepo.value = ApiSearchState.Error(e.message.toString())
            }
        }
    }

    private fun verifyResponse(response: Response<ResponseApi>) =
        if (response.isSuccessful) {
            response.body()?.let { responseApi ->
                _searchRepo.value = ApiSearchState.Success(responseApi)
            }
        } else {
            _searchRepo.value = ApiSearchState.Error(response.message().toString())
        }

    fun saveFavoriteInBD(infoRepo: InfoRepo) {
        launcher {

            weFitRepository.insert(infoRepo)
        }
    }
}