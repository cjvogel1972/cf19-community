package com.edwardjones.avengers.community.ui.chatlobby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edwardjones.avengers.community.R

class ChatLobbyFragment : Fragment() {

    private lateinit var chatLobbyViewModel: ChatLobbyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatLobbyViewModel =
            ViewModelProviders.of(this).get(ChatLobbyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat_lobby, container, false)
        val textView: TextView = root.findViewById(R.id.text_chat_lobby)
        chatLobbyViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}