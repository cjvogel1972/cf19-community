package com.edwardjones.avengers.community.ui.chatlobby

import androidx.lifecycle.ViewModel

class ChatLobbyViewModel : ViewModel() {

    val roomNames : List<String>

    init {
        roomNames = listOf("My Branch Team", "Region 77", "Area 2 BOA's", "Region 77 Leaders", "Double Sevens Golf club", "Sixth Room")
    }
}