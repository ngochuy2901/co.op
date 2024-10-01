package com.example.kotlinxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinxml.R
import com.example.kotlinxml.adapter.NotificationAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class NotificationFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        viewpager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
        val notificationAdapter = NotificationAdapter(this)
        viewpager.adapter = notificationAdapter
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Của bạn"
                1 -> "Khuyến mãi"
                else -> "Tin tức"
            }
        }.attach()
        return view
    }

}