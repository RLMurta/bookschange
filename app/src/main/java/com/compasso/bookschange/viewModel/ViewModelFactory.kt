package com.compasso.bookschange.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compasso.bookschange.model.home.bookApi.ApiInterface
import com.compasso.bookschange.model.home.bookApi.BooksRepository
import com.compasso.bookschange.viewModel.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == HomeViewModel::class.java) {
            return providerHomeViewModel() as T
        } else {
            throw Exception("View Model não definido")
        }
    }

    fun providerHomeViewModel(): HomeViewModel {
        return HomeViewModel(
            BooksRepository(
                providerApiInterface()
            )
        )
    }

    private fun providerApiInterface(): ApiInterface {
        return ApiInterface.create()
    }

}
