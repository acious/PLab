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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.acious.plabs.databinding.FragmentOrderBookBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderBookFragment : Fragment() {
    private var screenHalfSize: Int = 0
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

        this.screenHalfSize = 0
        activity?.let {
            screenHalfSize = it.window.decorView.width / 2
        }

        initOrderBookList()
    }

    private fun initOrderBookList() {
        val adapter = OrderBookListAdapter()
        binding.orderbookList.adapter = adapter
        binding.orderbookList.layoutManager = LinearLayoutManager(activity)
        val animator: RecyclerView.ItemAnimator? = binding.orderbookList.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
            animator.changeDuration = 0
        }

        viewModel.initView(screenHalfSize)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.visibleListStateFlow.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }
}