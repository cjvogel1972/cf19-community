package com.edwardjones.avengers.community.ui.chatlobby

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.edwardjones.avengers.community.R
import com.edwardjones.avengers.community.ui.chatroom.ChatRoomFragment

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
        val gridLayout = root.findViewById<GridLayout>(R.id.lobby_layout)
        val numRooms = chatLobbyViewModel.roomNames.size
        val totalRooms = numRooms + 1
        if (totalRooms < 3) {
            gridLayout.columnCount = totalRooms
            gridLayout.rowCount = 2
        } else {
            gridLayout.columnCount = 3
            var rowCount = totalRooms / 3
            if (totalRooms % 3 != 0) {
                rowCount++
            }
            rowCount *= 2
            Log.i("CHAT", "row count = $rowCount")
            gridLayout.rowCount = rowCount
        }

        var column = 0
        var row = 0
        for (i in 0 until numRooms) {
            var roomName = chatLobbyViewModel.roomNames[i]
            Log.i("CHAT", roomName)
            buildButton(i, root, row, column, gridLayout, R.drawable.ic_chat_room)

            buildButtonText(roomName, row + 1, column, gridLayout)

            column++
            if (column == 3) {
                column = 0
                row += 2
            }

        }

        buildButton(numRooms, root, row, column, gridLayout, R.drawable.ic_create_chat_room)
        buildButtonText("Create New Community", row + 1, column, gridLayout)

        return root
    }

    private fun buildButtonText(
        roomName: String,
        row: Int,
        column: Int,
        gridLayout: GridLayout
    ) {
        var roomText = TextView(context)
        roomText.text = roomName
        roomText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0f)
        roomText.ellipsize = null
        roomText.maxWidth = dpToPx(80, context)
        roomText.maxLines = 2
        val textParams = GridLayout.LayoutParams()
        textParams.columnSpec = GridLayout.spec(column)
        textParams.rowSpec = GridLayout.spec(row)
        textParams.leftMargin = dpToPx(8, context)
        textParams.rightMargin = dpToPx(8, context)
        textParams.bottomMargin = dpToPx(8, context)
        roomText.layoutParams = textParams
        gridLayout.addView(roomText)
    }

    private fun buildButton(
        i: Int,
        root: View,
        row: Int,
        column: Int,
        gridLayout: GridLayout,
        resId: Int
    ) {
        var button = ImageButton(context)
        button.id = 100 + i
        button.setImageResource(resId)
        val bg = root.resources.getDrawable(R.drawable.button_bg_dashed_border, null)
        button.background = bg
        val params = GridLayout.LayoutParams()
        params.height = dpToPx(80, context)
        params.width = dpToPx(80, context)
        params.leftMargin = dpToPx(8, context)
        params.rightMargin = dpToPx(8, context)
        params.bottomMargin = dpToPx(8, context)
        params.rowSpec = GridLayout.spec(row)
        params.columnSpec = GridLayout.spec(column)
        button.layoutParams = params
        button.scaleType = ImageView.ScaleType.FIT_CENTER
        button.setOnClickListener {
            val id = it.id - 100
            val roomName = chatLobbyViewModel.roomNames[id]
            val apptFragment = ChatRoomFragment(roomName)
            replaceFragment(apptFragment)

        }
        gridLayout.addView(button)
    }

    fun dpToPx(dp: Int, context: Context?): Int {
        val density = context?.getResources()?.getDisplayMetrics()?.density
        return Math.round(dp.toFloat() * density!!)
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace((view!!.parent as ViewGroup).id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}