package com.example.githubuserapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: ListUserAdapter
    private lateinit var username: String
    private var position: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION,0)
            username = it.getString(ARG_USERNAME).toString()
        }

        adapter = ListUserAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollower.layoutManager = layoutManager
        binding.rvFollower.adapter = adapter

        val followViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowViewModel::class.java]

        if(position == 1){
            followViewModel.getFollowers(username)
            followViewModel.listFollowers.observe(viewLifecycleOwner){
                    follow -> setFollow(follow)
            }
        }else{
            followViewModel.getFollowing(username)
            followViewModel.listFollowing.observe(viewLifecycleOwner)
            {
                follow -> setFollow(follow)
            }
        }

        followViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun setFollow(follow: List<ItemsItem?>?) {
        adapter.submitList(follow)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.pbFollower.visibility = View.VISIBLE
        }else{
            binding.pbFollower.visibility = View.GONE
        }
    }

    companion object {
        var ARG_USERNAME = "username"
        var ARG_POSITION = "section_number"
    }
}