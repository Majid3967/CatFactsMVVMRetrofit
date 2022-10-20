package com.example.catsquotes.api

import com.example.catsquotes.models.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuoteService {

    @GET("fact")
    suspend fun getQuote() : Response<Quote>
}