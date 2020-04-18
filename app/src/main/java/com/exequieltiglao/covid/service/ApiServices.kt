package com.exequieltiglao.covid.service

import com.exequieltiglao.covid.entity.Data
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("v2/total")
    fun data(): Single<Data>

    @GET("v2/country/{country_name}")
    fun country(@Path("country_name") country: String): Single<Data>

}

interface ApiRepository {
    fun data(): Single<Data>
    fun country(country: String): Single<Data>
}

class ApiRepositoryImpl(val apiServices: AppApi) :  ApiRepository {
    override fun data(): Single<Data> = apiServices.data()
    override fun country(country: String): Single<Data> = apiServices.country(country)
}