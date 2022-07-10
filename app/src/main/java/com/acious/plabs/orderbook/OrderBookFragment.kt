package com.acious.plabs.orderbook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.acious.plabs.databinding.FragmentOrderBookBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderBookFragment : Fragment() {
    private lateinit var binding: FragmentOrderBookBinding

    companion object {
        fun newInstance() = OrderBookFragment()
    }

    private lateinit var viewModel: OrderBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBookBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[OrderBookViewModel::class.java]

        initOrderBookList()
    }

    private fun initOrderBookList() {
        val adapter = OrderBookListAdapter()
        binding.orderbookList.adapter = adapter
        binding.orderbookList.layoutManager = LinearLayoutManager(activity)

        viewModel.initView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.visibleListStateFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}