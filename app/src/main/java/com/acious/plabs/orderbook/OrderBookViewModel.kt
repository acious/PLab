package com.acious.plabs.orderbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acious.plabs.api.OrderBookRepository
import com.acious.plabs.model.orderbook.OrderBookStorage
import com.acious.plabs.model.orderbook.OrderBookVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    var repository: OrderBookRepository
) : ViewModel() {
    private val _visibleListStateFlow = MutableStateFlow<List<OrderBookVO>>(emptyList())
    val visibleListStateFlow: StateFlow<List<OrderBookVO>> = _visibleListStateFlow

    private val orderBookStorage = OrderBookStorage()

    fun initView(screenHalfSize: Int) {
        orderBookStorage.setScreenHalfSize(screenHalfSize)
        viewModelScope.launch {
            repository.connectOrderBook(this).collectLatest {
                it.let {
                    orderBookStorage.handleData(it)
                    delay(100)
                    _visibleListStateFlow.value = orderBookStorage.makeVisibleList()
                }
            }
        }
    }
}