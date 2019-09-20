package com.edwardjones.avengers.community.ui.chatroom

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.edwardjones.avengers.community.R

class MessageAdapter(context: Context): BaseAdapter() {

    val context: Context = context
    val messages = mutableListOf<Message>()

    fun add(message: Message) {
        messages.add(message)
        notifyDataSetChanged() // to render the list we need to notify
    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(i: Int): Any {
        return messages[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder = MessageViewHolder()
        val messageInflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val message = messages[position]
        var newView : View

        if (message.sender == "Regional Leader") { // this message was sent by us so let's create a basic chat bubble on the right
            newView = messageInflater.inflate(R.layout.my_message, null)
            holder.messageBody = newView.findViewById(R.id.message_body) as TextView
            newView.setTag(holder)
            holder.messageBody!!.setText(message.content)
        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            newView = messageInflater.inflate(R.layout.their_message, null)
            holder.avatar = newView.findViewById(R.id.avatar) as View
            holder.name = newView.findViewById(R.id.name) as TextView
            holder.messageBody = newView.findViewById(R.id.message_body) as TextView
            newView.setTag(holder)

            holder.name!!.setText(message.sender)
            holder.messageBody!!.setText(message.content)
            val drawable = holder.avatar!!.getBackground() as GradientDrawable
            drawable.setColor(Color.parseColor("blue"))
        }

        return newView
    }

}