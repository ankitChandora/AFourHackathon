package com.example.afourhackathon.ui.gift

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afourhackathon.data.DataRepository
import com.example.afourhackathon.data.model.Gift
import com.example.afourhackathon.data.network.ApiResult
import com.example.afourhackathon.util.Event
import kotlinx.coroutines.launch

/**
 * Created by ChandoraAnkit on 17/11/22
 */

class GiftViewModel : ViewModel() {

    private val giftsRepository by lazy { DataRepository.getInstance() }

    private val _gifts by lazy { MutableLiveData<List<Gift>>() }
    val gifts: LiveData<List<Gift>> = _gifts

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: MutableLiveData<Event<String>> = _showToast

    private val _availableCoins by lazy { MutableLiveData<Int>() }
    val availableCoins: LiveData<Int> = _availableCoins

    fun fetchGifts() {
        viewModelScope.launch {
            val result = giftsRepository.fetchGifts()
            if (result is ApiResult.Success) {
                _gifts.value = result.data.gifts

                val coins = result.data.availableCoins
                if (coins != null){
                    _availableCoins.value = coins
                }
            } else if (result is ApiResult.Error) {
                val message = result.exception.message ?: "Something went wrong"
                _showToast.value = Event(message)
            }
        }
    }

    fun updateAvailableCoins(coins: Int){
         _availableCoins.value?.let {  remainingCoins ->
             if (remainingCoins >= coins){
                 _availableCoins.value = remainingCoins - coins
             }else{
                 _showToast.value = Event("You don't have enough coins to buy this gift.")
             }
         }
    }

}