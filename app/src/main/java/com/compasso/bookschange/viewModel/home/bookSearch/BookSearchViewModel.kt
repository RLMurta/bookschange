package com.compasso.bookschange.viewModel.home.bookSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compasso.bookschange.model.home.bookApi.BookNotFoundException
import com.compasso.bookschange.model.home.bookApi.BooksRepository
import com.compasso.bookschange.model.home.bookApi.BooksResponse
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.model.room.Book
import kotlinx.coroutines.launch
import java.lang.Exception

class BookSearchViewModel(private val repository: BooksRepository, private val db: AppDatabase) :
    ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _booksList = MutableLiveData<List<BooksResponse>>()
    val booksList: LiveData<List<BooksResponse>> = _booksList
    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit> = _success
    private var searchTerms: String? = null
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchBooks() {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val response = repository.fetch(searchTerms!!)
                _booksList.postValue(response.items)
                _success.postValue(Unit)
            } catch (e: BookNotFoundException) {
                _error.postValue("Livro não encontrado")
            } catch (e: Exception) {
                _error.postValue("Ocorreu um erro")
            }
            _loading.postValue(false)
        }
    }

    private fun setSearchTerms(searchTerms: String): Boolean {
        if (searchTerms.isNullOrBlank()) {
            _error.postValue("Favor digitar o nome de um livro antes de buscar")
        } else {
            this.searchTerms = searchTerms
        }
        return searchTerms.isNullOrBlank()
    }

    fun onConfirmButtonClicked(searchTerms: String) {
        if (!setSearchTerms(searchTerms)) {
            fetchBooks()
        }
    }

    fun insertBookIntoDb(title: String, thumbnailLink: String?) {
        var book: Book
        viewModelScope.launch {
            book = Book(
                title,
                thumbnailLink
            )
            db.booksDao().insertAll(book)
        }
    }
}
