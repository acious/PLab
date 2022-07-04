package com.acious.plabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.acious.plabs.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: AQXMainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val pager = binding.mainPager
        pagerAdapter = AQXMainPagerAdapter(this)
        pager.adapter = pagerAdapter
        TabLayoutMediator(binding.mainTab, binding.mainPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Order Book"
                }
                1 -> {
                    tab.text = "Recent Trades"
                }
            }
        }.attach()
    }
}