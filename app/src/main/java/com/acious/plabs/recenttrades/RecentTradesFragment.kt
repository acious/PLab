package com.acious.plabs.recenttrades

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acious.plabs.databinding.FragmentRecentTradesBinding

class RecentTradesFragment : Fragment() {
    private lateinit var binding: FragmentRecentTradesBinding

    companion object {
        fun newInstance() = RecentTradesFragment()
    }

    private lateinit var viewModel: RecentTradesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentTradesBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RecentTradesViewModel::class.java]

        initRecentTradeList()
    }

    private fun initRecentTradeList() {
        val adapter = RecentTradeListAdapter()
        binding.recentTradesList.adapter = adapter
    }
}