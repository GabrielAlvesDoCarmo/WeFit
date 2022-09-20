package com.gdsdesenvolvimento.wefit.data.datasource.api

import com.gdsdesenvolvimento.wefit.data.model.responseApi.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeFitAPI {
    @GET("/{users}/repos ")
    suspend fun getRepo(
        @Path(value = "users") repo : String
    ) : Response<ResponseApi>
}