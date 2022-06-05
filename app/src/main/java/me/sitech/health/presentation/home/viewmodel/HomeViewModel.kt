package me.sitech.health.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.sitech.health.app.utils.RequestState
import me.sitech.health.domain.usecase.RedeemUseCase
import okhttp3.ResponseBody

class HomeViewModel constructor(private val useCase: RedeemUseCase) : ViewModel() {

    private val mRedeemDataResult =
        MutableStateFlow<RequestState<ResponseBody>>(RequestState.Loading(true))
    val mRedeemStateFlow: StateFlow<RequestState<ResponseBody>> get() = mRedeemDataResult

    fun redeem() {
        viewModelScope.launch {
            useCase().collect {
                mRedeemDataResult.value = it
            }
        }
    }
}