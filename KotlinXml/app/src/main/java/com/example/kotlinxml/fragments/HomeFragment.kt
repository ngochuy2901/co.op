package com.example.kotlinxml.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlinxml.R
import com.example.kotlinxml.activities.ProductActivity


class HomeFragment : Fragment() {
    private lateinit var productTextview:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View =  inflater.inflate(R.layout.fragment_home, container, false)
        productTextview = view.findViewById(R.id.product)
        productTextview.setOnClickListener(View.OnClickListener {
            startActivity(Intent(requireContext(), ProductActivity::class.java))
        })
        return view
    }
}