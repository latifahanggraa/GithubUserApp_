package com.example.githubuserapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.response.DetailUserResponse
import com.example.githubuserapp.databinding.FragmentFollowerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: ListUserAdapter
    private lateinit var username: String
    private lateinit var viewModel: FollowViewModel
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username= it.getString(ARG_USERNAME)!!
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollower.layoutManager = layoutManager

        if (isAdded && isDetached){
            val followViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[FollowViewModel::class.java]

            followViewModel.detailUser.observe(viewLifecycleOwner){
                showLoading(it)
            }
        }
    }

    private fun showLoading(detailUser: DetailUserResponse) {

    }

    companion object {
        var ARG_USERNAME: String = ""
        var ARG_POSITION = "section_number"
    }
}