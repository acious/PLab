package com.acious.plabs.orderbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acious.plabs.api.OrderBookRepository
import com.acious.plabs.model.OrderBookStorage
import com.acious.plabs.model.OrderBookVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    var repository: OrderBookRepository
) : ViewModel() {
    private val _visibleListStateFlow = MutableStateFlow<List<OrderBookVO>>(emptyList())
    val visibleListStateFlow: StateFlow<List<OrderBookVO>> = _visibleListStateFlow

    private val orderBookStorage = OrderBookStorage()

    fun initView() {
        viewModelScope.launch {
            repository.connectOrderBook(this).collect {
                it.let {
                    orderBookStorage.handleData(it)
                    _visibleListStateFlow.value = orderBookStorage.getVisibleList()
                }
            }
        }
    }
}