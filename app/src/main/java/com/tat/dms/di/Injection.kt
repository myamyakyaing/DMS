package com.tat.dms.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tat.dms.db.MyDatabase
import com.tat.dms.network.ApiService
import com.tat.dms.repositories.*
import com.tat.dms.util.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injection {
    private fun provideApiService(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.base_url)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun provideDatabase(context:Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }
    fun provideMainRepository(context: Context): MainRepository {
        return MainRepositoryImpl(context,provideApiService(),provideDatabase(context))
    }
    fun provideSaleItemRepository(context: Context): SaleItemRepository {
        return SaleItemRepositoryImp(context,provideApiService(),provideDatabase(context))
    }
    fun provideCheckoutRepository(context: Context): CheckoutRepository {
        return CheckoutRepositoryImpl(context,provideApiService(), provideDatabase(context))
    }
    fun provideReportRepository(context: Context): RepotrRepository {
        return RepotrRepositoryImpl(provideDatabase(context))
    }
    fun provideSaleReportRepository(context: Context): SaleRepotrRepository {
        return SaleRepotrRepositoryImpl(provideDatabase(context))
    }


}