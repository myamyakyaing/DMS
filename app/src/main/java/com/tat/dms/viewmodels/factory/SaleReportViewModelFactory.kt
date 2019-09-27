package com.tat.dms.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tat.dms.repositories.SaleRepotrRepository
import com.tat.dms.viewmodels.SaleReportViewModel

class SaleReportViewModelFactory (val repo: SaleRepotrRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SaleReportViewModel(repo) as T
    }
}
