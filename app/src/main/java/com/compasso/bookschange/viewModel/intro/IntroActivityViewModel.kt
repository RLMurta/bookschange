package com.compasso.bookschange.viewModel.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroActivityViewModel: ViewModel() {
    private val _isAccessLocationPermissionAccepted = MutableLiveData<Unit>()
    val isAccessLocationPermissionAccepted: LiveData<Unit> = _isAccessLocationPermissionAccepted

    fun setLocationPermissionStatus() {
        _isAccessLocationPermissionAccepted.postValue(Unit)
    }
}