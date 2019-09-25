package com.tat.dms.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tat.dms.repositories.MainRepository
import com.tat.dms.viewmodels.MainViewModel

class MainViewModelFactory (val repo: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}
