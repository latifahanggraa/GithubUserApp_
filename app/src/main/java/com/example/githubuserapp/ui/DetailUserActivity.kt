package com.example.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.data.response.DetailUserResponse
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getStringExtra("user")

        val followViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowViewModel::class.java]

        followViewModel.getDetailUser(userId.toString())

        followViewModel.detailUser.observe(this) { user ->
            setDetailUser(user)
        }

        followViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionPagerAdapter = SectionsPagerAdapter(this)
        sectionPagerAdapter.login = userId.toString()

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
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