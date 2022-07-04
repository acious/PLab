package com.acious.plabs.orderbook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acious.plabs.R

class OrderBookFragment : Fragment() {

    companion object {
        fun newInstance() = OrderBookFragment()
    }

    private lateinit var viewModel: OrderBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderBookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}