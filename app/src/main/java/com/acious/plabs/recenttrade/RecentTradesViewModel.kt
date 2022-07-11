package com.acious.plabs.recenttrade

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acious.plabs.api.RecentTradeRepository
import com.acious.plabs.model.recenttrade.RecentTradeStorage
import com.acious.plabs.model.recenttrade.RecentTradeVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentTradesViewModel @Inject constructor(
    var repository: RecentTradeRepository
) : ViewModel() {
    private val _recentTradeDataStateFlow = MutableStateFlow<List<RecentTradeVO>>(emptyList())
    val recentTradeDataStateFlow: StateFlow<List<RecentTradeVO>> = _recentTradeDataStateFlow

    private val recentTradeStorage = RecentTradeStorage()

    fun initView() {
        viewModelScope.launch {
            repository.connectRecentTrade(this).collect {
                it.let {
                    recentTradeStorage.handleData(it)
                    _recentTradeDataStateFlow.value = recentTradeStorage.getVisibleList()
                }
            }
        }
    }
}