package com.edwardjones.avengers.community.ui.chatroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.edwardjones.avengers.community.R
import com.fasterxml.jackson.databind.ObjectMapper

import ua.naiksoftware.stomp.LifecycleEvent
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.client.StompClient
import ua.naiksoftware.stomp.client.StompMessage

//import com.techdew.stomplibrary.LifecycleEvent
//import com.techdew.stomplibrary.Stomp
//import com.techdew.stomplibrary.StompClient
//import com.techdew.stomplibrary.StompMessage
//import org.java_websocket.WebSocket

class ChatRoomFragment(room: ChatRoom) : Fragment() {

    private lateinit var chatRoomViewModel: ChatRoomViewModel
    private val room: ChatRoom = room

    private lateinit var mStompClient: StompClient
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messagesView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatRoomViewModel =
            ViewModelProviders.of(this).get(ChatRoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat_room, container, false)

        messageAdapter = context?.let { MessageAdapter(it) }!!
        messagesView = root.findViewById<ListView>(R.id.messages_view)
        messagesView.adapter = messageAdapter
        messageAdapter.add(Message("CHAT", "Hello Codefest", "ADS Avengers"))
        messageAdapter.add(Message("CHAT", "Hello Avengers", "Regional Leader"))

        val sendButton = root.findViewById<ImageButton>(R.id.send_message)
        val editText = root.findViewById<EditText>(R.id.editText)

        createStompClient()
        Log.i("CHAT", "room name = ${room.name}")
        sendButton.setOnClickListener {
            val message = editText.text.toString()
            if (message.isNotEmpty()) {
                // send message on websocket
                val mapper = ObjectMapper()
                val message = Message("CHAT", message, "Regional Leader")
//                val json = "{ \"type\":\"CHAT\", \"sender\": \"Regional Leader\", \"content\": \"$message\""
                val json = mapper.writeValueAsString(message)
                Log.i("CHAT", "${room.id} $json")
//                if (!mStompClient.isConnected) {
//                    Log.i(TAG, "Reconnecting")
//                    mStompClient.reconnect()
//                }
                mStompClient.send("/app/chat.sendMessage/${room.id}", json)
                editText.text.clear()
            }
        }

        return root
    }

    private fun createStompClient() {

        Log.i(TAG, "Before stomp client creation")
        try {
//            mStompClient = Stomp.over(
//                WebSocket::class.java, "ws://" + STOMP_BASE_URL
//                        + "/ws/websocket")
            mStompClient = Stomp.over(
                Stomp.ConnectionProvider.OKHTTP, "ws://" + STOMP_BASE_URL
                        + "/ws/websocket"
            )
            mStompClient.setHeartbeat(1000)
            Log.i(TAG, "After stomp client creation")
            mStompClient.lifecycle().subscribe { lifecycleEvent ->
                when (lifecycleEvent.getType()) {

                    LifecycleEvent.Type.OPENED -> Log.i(TAG, "*******Stomp connection opened")

                    LifecycleEvent.Type.ERROR -> Log.e(TAG, "Error", lifecycleEvent.getException())

                    LifecycleEvent.Type.CLOSED -> Log.i(TAG, "Stomp connection closed")
                }
            }
            Log.i(TAG, "Before stomp client connection")
            mStompClient.connect()
            Log.i(TAG, "After stomp client connection")


            mStompClient.topic("/topic/public/${room.id}")
                .subscribe{ topicMessage -> onMessage(topicMessage) }
        } catch (e: Throwable) {
            Log.e(TAG, "Some error!", e)
            Log.e(TAG, "The cause:", e.cause)
        }

    }

    private fun onMessage(topicMessage: StompMessage) {
        Log.d(TAG, topicMessage.getPayload())
        val mapper = ObjectMapper()
        val message = mapper.readValue(topicMessage.payload, Message::class.java)
        activity?.runOnUiThread {
            messageAdapter.add(message)
            messagesView.setSelection(messagesView.count - 1)
        }
    }

    companion object {
        const val STOMP_BASE_URL = "chatapp.codefest.ads-avengers.com"
        const val TAG = "CHAT"
    }
}