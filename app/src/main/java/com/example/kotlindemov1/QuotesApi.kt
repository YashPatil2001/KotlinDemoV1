package com.example.kotlindemov1

import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {

    @GET("/quotesw")
    suspend fun getQuotes(): Response<QuoteList>
}