package com.compasso.bookschange.model.home.bookApi

class BooksRepository (private val api: ApiInterface){
    suspend fun fetch(searchTerms: String) : List<BooksResponse> {
        val response = api.fetch(searchTerms)
        if(response.isSuccessful){
            return response.body()!!
        } else if(response.code() == BOOK_NOT_FOUND){
            throw BookNotFoundException()
        }else {
            throw Exception()
        }
    }

    companion object{
        private const val BOOK_NOT_FOUND = 404
    }
}
