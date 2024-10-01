package com.example.kotlinxml.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlinxml.fragments.bill_fragment.HistoryFragment
import com.example.kotlinxml.fragments.bill_fragment.OrderProcessingFragment

class OrderAdapter (fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        // Số lượng tab (số fragment)
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        // Trả về fragment tương ứng với mỗi tab
        return when (position) {
            0 -> OrderProcessingFragment()
            1 -> HistoryFragment()
            else -> HistoryFragment()
        }
    }
}