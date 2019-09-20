package com.edwardjones.avengers.community.ui.chatroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edwardjones.avengers.community.R

class ChatRoomFragment : Fragment() {

    private lateinit var chatRoomViewModel: ChatRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatRoomViewModel =
            ViewModelProviders.of(this).get(ChatRoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat_room, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        chatRoomViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}