package com.example.kotlinxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinxml.R
import com.example.kotlinxml.adapter.NotificationAdapter
import com.example.kotlinxml.adapter.OrderAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OrderFragment : Fragment() {
    private lateinit var viewpager : ViewPager2
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_order, container, false)
        viewpager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
        val orderAdapter = OrderAdapter(this)
        viewpager.adapter = orderAdapter
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Đơn hàng đang xử lý"
                else -> "Lịch sử đơn hàng"
            }
        }.attach()
        return view
    }


}