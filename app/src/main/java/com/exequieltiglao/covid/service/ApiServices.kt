package com.exequieltiglao.covid.service

import com.exequieltiglao.covid.entity.CDRresults
import io.reactivex.Single
import retrofit2.http.GET

interface AppApi {
    @GET("api/cdr")
    fun cdr(): Single<List<CDRresults>>
}

interface ApiRepository {
    fun cdr(): Single<List<CDRresults>>
}

class ApiRepositoryImpl(val apiServices: AppApi) :  ApiRepository {
    override fun cdr(): Single<List<CDRresults>> = apiServices.cdr()
}