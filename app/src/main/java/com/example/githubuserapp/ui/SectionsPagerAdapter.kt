package com.example.githubuserapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var login: String = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowerFragment()

        fragment.arguments = Bundle().apply {
            putInt(FollowerFragment.ARG_POSITION,position + 1)
            putString(FollowerFragment.ARG_USERNAME,login)
        }

        return fragment
    }

}