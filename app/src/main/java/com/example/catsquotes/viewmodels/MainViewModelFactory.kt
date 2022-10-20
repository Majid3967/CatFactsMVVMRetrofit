package com.example.catsquotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catsquotes.repository.QuoteRepsoitory

class MainViewModelFactory(private val repository:QuoteRepsoitory) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}