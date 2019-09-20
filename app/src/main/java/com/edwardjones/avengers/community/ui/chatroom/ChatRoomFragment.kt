package com.edwardjones.avengers.community.ui.chatroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.edwardjones.avengers.community.R
import kotlinx.android.synthetic.main.fragment_chat_room.*

class ChatRoomFragment(roomName: String) : Fragment() {

    private lateinit var chatRoomViewModel: ChatRoomViewModel
    private val roomName: String = roomName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatRoomViewModel =
            ViewModelProviders.of(this).get(ChatRoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat_room, container, false)

        val sendButton = root.findViewById<ImageButton>(R.id.send_message)
        val editText = root.findViewById<EditText>(R.id.editText)

        Log.i("CHAT", "room name = $roomName")
        sendButton.setOnClickListener {
            val message = editText.text.toString()
            if (message.isNotEmpty()) {
                // send message on websocket
                editText.text.clear()
            }
        }

        return root
    }
}