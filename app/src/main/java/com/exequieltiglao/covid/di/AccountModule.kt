package com.exequieltiglao.covid.di

import com.exequieltiglao.covid.service.ApiRepository
import com.exequieltiglao.covid.service.ApiRepositoryImpl
import com.exequieltiglao.covid.service.AppApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class AccountModule {

    @Module
    companion object {
        const val BASE_URL = "https://2019ncov.asia/"

        @JvmStatic
        @Provides
        fun provideAppApi(retrofit: Retrofit): AppApi {
            return retrofit.create(AppApi::class.java)
        }

        @JvmStatic
        @Provides
        internal fun provideApiRepository(api: AppApi): ApiRepository {
            return ApiRepositoryImpl(api)
        }
    }
}