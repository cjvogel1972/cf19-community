package com.edwardjones.avengers.community.ui.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is region Fragment"
    }
    val text: LiveData<String> = _text
}