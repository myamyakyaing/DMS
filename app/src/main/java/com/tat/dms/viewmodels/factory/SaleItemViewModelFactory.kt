package com.tat.dms.viewmodels.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tat.dms.repositories.SaleItemRepository
import com.tat.dms.viewmodels.SaleItemViewModel

class SaleItemViewModelFactory(val repo: SaleItemRepository,val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SaleItemViewModel(repo,context) as T
    }
}
