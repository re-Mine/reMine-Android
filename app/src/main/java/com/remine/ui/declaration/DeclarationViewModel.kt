package com.remine.ui.declaration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeclarationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is declaration Fragment"
    }
    val text: LiveData<String> = _text
}