package com.acious.plabs.recenttrades

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acious.plabs.R

class RecentTradesFragment : Fragment() {

    companion object {
        fun newInstance() = RecentTradesFragment()
    }

    private lateinit var viewModel: RecentTradesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_trades, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecentTradesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}