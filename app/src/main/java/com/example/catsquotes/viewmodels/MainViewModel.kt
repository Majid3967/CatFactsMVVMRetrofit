package com.example.catsquotes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsquotes.models.Quote
import com.example.catsquotes.repository.QuoteRepsoitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepsoitory) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote()
        }

    }

    val quote: LiveData<Quote>
        get() = repository.quote

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote()
        }
    }
}