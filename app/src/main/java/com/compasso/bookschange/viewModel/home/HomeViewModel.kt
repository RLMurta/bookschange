package com.compasso.bookschange.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compasso.bookschange.model.Constants.Companion.DETACHMENT_LIST_OPTION
import com.compasso.bookschange.model.Constants.Companion.WISHLIST_OPTION
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.model.room.Book
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val _myBookWhishlist = MutableLiveData<List<Book>>()
    val myBookWhishlist: LiveData<List<Book>> = _myBookWhishlist
    private val _myBookDetachmentlist = MutableLiveData<List<Book>>()
    val myBookDetachmentlist: LiveData<List<Book>> = _myBookDetachmentlist
    private val _startLoading = MutableLiveData<Unit>()
    val startLoading: LiveData<Unit> = _startLoading
    private val _stopLoading = MutableLiveData<Unit>()
    val stopLoading: LiveData<Unit> = _stopLoading

    fun fetchMyBooks(db: AppDatabase, option: Int) {
        viewModelScope.launch {
            _startLoading.postValue(Unit)
            when (option){
                WISHLIST_OPTION -> {
                    _myBookWhishlist.postValue(db.booksDao().getAll())
                }
                DETACHMENT_LIST_OPTION -> {
                    _myBookDetachmentlist.postValue(db.booksDao().getAll())
                }
            }
            _stopLoading.postValue(Unit)
        }
    }
}
