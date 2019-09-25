package com.tat.dms.viewmodels.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tat.dms.repositories.CheckoutRepository
import com.tat.dms.viewmodels.CheckoutViewModel

class CheckoutViewModelFactory (private val repository: CheckoutRepository,private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CheckoutViewModel(repository,context) as T
    }
}