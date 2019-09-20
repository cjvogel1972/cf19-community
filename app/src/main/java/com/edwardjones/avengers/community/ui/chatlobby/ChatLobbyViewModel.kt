package com.edwardjones.avengers.community.ui.chatlobby

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatLobbyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is messaging Fragment"
    }
    val text: LiveData<String> = _text
}