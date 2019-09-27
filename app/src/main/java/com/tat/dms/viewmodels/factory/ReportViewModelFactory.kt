package com.tat.dms.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tat.dms.repositories.RepotrRepository
import com.tat.dms.viewmodels.ReportViewModel

class ReportViewModelFactory (val repo: RepotrRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReportViewModel(repo) as T
    }
}
