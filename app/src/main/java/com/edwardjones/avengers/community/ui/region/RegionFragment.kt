package com.edwardjones.avengers.community.ui.region

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edwardjones.avengers.community.R

class RegionFragment : Fragment() {

    private lateinit var regionViewModel: RegionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        regionViewModel =
            ViewModelProviders.of(this).get(RegionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_region, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        regionViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}