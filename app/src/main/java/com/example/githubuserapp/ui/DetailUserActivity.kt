package com.example.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.example.githubuserapp.databinding.ListUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("USER_ID")

        binding.profileImage
        binding.tvItemName
        binding.tvUsername
        binding.tvFollowers
        binding.tvFollowing
        binding.tabs
        binding.viewPager

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    

}