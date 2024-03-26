package com.example.retrofitcallapidemo.ui.home.tablayout

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FollowFragment()
            }

            1 -> {
                ForYouFragment()
            }

            2 -> {
                FootballFragment()
            }

            3 -> {
                TechFragment()
            }

            4 -> {
                LifeFragment()
            }

            else -> {
                throw Resources.NotFoundException("Position Not Found")
            }
        }
    }
}