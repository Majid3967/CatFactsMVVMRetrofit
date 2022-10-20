package com.example.catsquotes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catsquotes.api.QuoteService
import com.example.catsquotes.models.Quote

class QuoteRepsoitory(private val quoteService: QuoteService) {

    private val quoteLiveData = MutableLiveData<Quote>()

    val quote: LiveData<Quote>
        get() = quoteLiveData

    suspend fun getQuote() {
        val result = quoteService.getQuote()
        if (result?.body() != null) {
            quoteLiveData.postValue(result.body())
        }

    }
}