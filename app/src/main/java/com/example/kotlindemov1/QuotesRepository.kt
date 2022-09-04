package com.example.kotlindemov1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

class QuotesRepository(private val quoteService: QuotesApi) {

    private val quoteList = MutableLiveData<Response<QuoteList>>()
    private val TAG = "QuotesRepository"
    val quotes: LiveData<Response<QuoteList>>
    get() = quoteList


    suspend fun getQuotes(){
        val result = quoteService.getQuotes();
        try {
            Log.e(TAG, "getQuotes: >>>>>> ${result.code()}")
            if(result.isSuccessful){
                quoteList.postValue(Response.Success(result.body()))
            } else {
                quoteList.postValue(Response.Error(result.message(), errorCode = result.code()))
            }

        }catch (e : Exception) {
              quoteList.postValue(Response.Error(e.message.toString()))
        }

    }
}