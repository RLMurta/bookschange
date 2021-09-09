package com.compasso.bookschange.viewModel.home.bookSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compasso.bookschange.model.home.bookApi.BookNotFoundException
import com.compasso.bookschange.model.home.bookApi.BooksRepository
import com.compasso.bookschange.model.home.bookApi.BooksResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class BookSearchViewModel(private val repository: BooksRepository) : ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _booksList = MutableLiveData<List<BooksResponse>>()
    val booksList: LiveData<List<BooksResponse>> = _booksList
    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit> = _success
    private var searchTerms: String? = null

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val response = repository.fetch(searchTerms!!)
                _booksList.postValue(response.items)
                _success.postValue(Unit)
            } catch (e: BookNotFoundException) {
                _error.postValue("Livro não encontrado")
            } catch (e: Exception) {
                _error.postValue("Ocorreu um erro")
            }
        }
    }

    private fun setSearchTerms (searchTerms: String): Boolean {
        if(searchTerms.isNullOrBlank()) {
            _error.postValue("Favor digitar o nome de um livro antes de buscar")
        } else {
            this.searchTerms = searchTerms
        }
        return searchTerms.isNullOrBlank()
    }

    fun onConfirmButtonClicked(searchTerms: String) {
        if(!setSearchTerms(searchTerms)) {
            fetchBooks()
        }
    }
}
