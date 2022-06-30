package com.ka.favcin.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Константин"
    }
    val text: LiveData<String> = _text
    private val _text1 = MutableLiveData<String>().apply {
        value = "dog@mail.com"
    }
    val text1: LiveData<String> = _text1
//    fun texts:LiveData<MutableList<String>>
}