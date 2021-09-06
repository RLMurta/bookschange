package com.compasso.bookschange.model.home.bookApi

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("volumes")
    suspend fun fetch(
        @Query("q") searchTerms: String
    ) : Response<List<BooksResponse>>

    companion object{
        fun create() : ApiInterface{
            return Retrofit.Builder().baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
