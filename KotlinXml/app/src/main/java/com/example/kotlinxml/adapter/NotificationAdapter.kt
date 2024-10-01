package com.example.kotlinxml.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlinxml.fragments.notification_fragment.NewFragment
import com.example.kotlinxml.fragments.notification_fragment.SaleFragment
import com.example.kotlinxml.fragments.notification_fragment.YourFragment

class NotificationAdapter (fragment : Fragment) : FragmentStateAdapter (fragment) {
    override fun getItemCount(): Int {
        // Số lượng tab (số fragment)
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // Trả về fragment tương ứng với mỗi tab
        return when (position) {
            0 -> YourFragment()
            1 -> SaleFragment()
            else -> NewFragment()
        }
    }

}