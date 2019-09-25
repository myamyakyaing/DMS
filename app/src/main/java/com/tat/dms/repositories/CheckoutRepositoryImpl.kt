package com.tat.dms.repositories

import android.content.Context
import com.tat.dms.network.ApiService

class CheckoutRepositoryImpl(
    private val context: Context,
    private val mApiService: ApiService
) : CheckoutRepository {
}