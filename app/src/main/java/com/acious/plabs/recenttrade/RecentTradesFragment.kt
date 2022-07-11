package com.acious.plabs.recenttrade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.acious.plabs.databinding.FragmentRecentTradesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
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
        binding.recentTradesList.layoutManager = LinearLayoutManager(activity)

        viewModel.initView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recentTradeDataStateFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}