package com.compasso.bookschange.model.main.home.bookApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("volumes")
    suspend fun fetch(
        @Query("q") searchTerms: String
    ) : Response<BookListResponse>

    companion object{
        fun create() : ApiInterface{
            return Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(providerClient())
                .build()
                .create(ApiInterface::class.java)
        }

        fun providerClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .apply {
                    addInterceptor(AuthInterceptor())
                    addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                }
                .build()
        }
    }
}
