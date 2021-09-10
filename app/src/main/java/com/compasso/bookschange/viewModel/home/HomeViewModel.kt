package com.compasso.bookschange.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.model.room.Book
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val _myBookWhishlist = MutableLiveData<List<Book>>()
    val myBookWhishlist: LiveData<List<Book>> = _myBookWhishlist
    private val _myBookDetachmentlist = MutableLiveData<List<Book>>()
    val myBookDetachmentlist: LiveData<List<Book>> = _myBookDetachmentlist

    fun fetchMyBooks(db: AppDatabase, option: Int) {
        viewModelScope.launch {
            when (option){
                0 -> {
                    _myBookWhishlist.postValue(db.booksDao().getAll())
                }
                1 -> {
                    _myBookDetachmentlist.postValue(db.booksDao().getAll())
                }
            }

        }
    }
}
