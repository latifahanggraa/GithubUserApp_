package com.example.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapp.data.response.DetailUserResponse
import com.example.githubuserapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getStringExtra("user")
        Log.d("user id",userId.toString())

        val followViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[FollowViewModel::class.java]

        followViewModel.getDetailUser(userId.toString())

        followViewModel.detailUser.observe(this){
            user -> setDetailUser(user)
        }
    }

    private fun setDetailUser(user: DetailUserResponse?) {
        Glide.with(this).load(user?.avatarUrl).into(binding.profileImage)
        binding.tvUsername.text = user?.login
        binding.tvItemName.text = user?.name
        "${user?.followers} Follower".also { binding.tvFollowers.text = it }
        "${user?.following} Following".also { binding.tvFollowing.text = it }
    }

}