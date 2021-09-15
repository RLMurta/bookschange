package com.compasso.bookschange.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.compasso.bookschange.model.main.home.bookApi.ApiInterface
import com.compasso.bookschange.model.main.home.bookApi.BooksRepository
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.viewModel.main.home.HomeViewModel
import com.compasso.bookschange.viewModel.main.home.bookSearch.BookSearchViewModel

class ViewModelFactory(private val context: Context, private val databaseName: String?) : ViewModelProvider.Factory {
    constructor(context: Context) : this(context, null)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == HomeViewModel::class.java) {
            return providerHomeViewModel() as T
        } else if(modelClass == BookSearchViewModel::class.java) {
            return providerBookSearchViewModel() as T
        } else {
            throw Exception("View Model não definido")
        }
    }

    fun providerHomeViewModel(): HomeViewModel {
        return HomeViewModel()
    }

    fun providerBookSearchViewModel(): BookSearchViewModel {
        return BookSearchViewModel(
            BooksRepository(
                providerApiInterface()
            ),
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, databaseName!!
            ).build()
        )
    }

    private fun providerApiInterface(): ApiInterface {
        return ApiInterface.create()
    }

}
