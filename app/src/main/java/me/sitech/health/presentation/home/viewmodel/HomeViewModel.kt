package me.sitech.health.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.sitech.health.app.utils.RequestState
import me.sitech.health.data.model.StepEntity
import me.sitech.health.domain.usecase.DeleteStepRecordUseCase
import me.sitech.health.domain.usecase.InsertStepRecordUseCase
import me.sitech.health.domain.usecase.RedeemUseCase
import me.sitech.health.domain.usecase.StepRecordsUseCase
import okhttp3.ResponseBody

class HomeViewModel constructor(
    private val redeemUseCase: RedeemUseCase,
    private val stepRecordsUseCase: StepRecordsUseCase,
    private val insertStepRecordUseCase: InsertStepRecordUseCase,
    private val deleteStepRecordUseCase: DeleteStepRecordUseCase
) : ViewModel() {

    private val mRedeemDataResult =
        MutableStateFlow<RequestState<ResponseBody>>(RequestState.Loading(true))
    val mRedeemStateFlow: StateFlow<RequestState<ResponseBody>> get() = mRedeemDataResult

    private val mStepRecordsListDataResult =
        MutableStateFlow<RequestState<List<StepEntity>>>(RequestState.Loading(true))
    val mStepRecordsListStateFlow: StateFlow<RequestState<List<StepEntity>>> get() = mStepRecordsListDataResult

    private val mInsertRecordDataResult =
        MutableStateFlow<RequestState<Unit>>(RequestState.Loading(true))
    val mInsertRecordStateFlow: StateFlow<RequestState<Unit>> get() = mInsertRecordDataResult

    private val mDeleteRecordDataResult =
        MutableStateFlow<RequestState<Unit>>(RequestState.Loading(true))
    val mDeleteRecordStateFlow: StateFlow<RequestState<Unit>> get() = mDeleteRecordDataResult

    fun redeem() {
        viewModelScope.launch {
            redeemUseCase().collect {
                mRedeemDataResult.value = it
            }
        }
    }

    fun getStepRecordsList() {
        viewModelScope.launch {
            stepRecordsUseCase().collect {
                mStepRecordsListDataResult.value = it
            }
        }
    }

    fun insertStepRecord(dateTime: Long, count: Int) {
        val stepEntity = StepEntity(0, dateTime, count)
        viewModelScope.launch {
            insertStepRecordUseCase(stepEntity).collect {
                mInsertRecordDataResult.value = it
            }
        }
    }

    fun deleteStepRecord(stepEntity: StepEntity) {
        viewModelScope.launch {
            deleteStepRecordUseCase(stepEntity).collect {
                mDeleteRecordDataResult.value = it
            }
        }
    }
}