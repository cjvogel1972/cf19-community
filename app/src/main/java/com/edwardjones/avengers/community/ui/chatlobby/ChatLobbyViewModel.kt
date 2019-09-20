package com.edwardjones.avengers.community.ui.chatlobby

import androidx.lifecycle.ViewModel
import com.edwardjones.avengers.community.ui.chatroom.ChatRoom

class ChatLobbyViewModel : ViewModel() {

    val rooms: List<ChatRoom>

    init {
        rooms = listOf(
            ChatRoom(1, "My Branch Team"),
            ChatRoom(2, "Region 77"),
            ChatRoom(3, "Area 2 BOA's"),
            ChatRoom(4, "Region 77 Leaders"),
            ChatRoom(5, "Double Sevens Golf club"),
            ChatRoom(6, "Sixth Room")
        )
    }
}