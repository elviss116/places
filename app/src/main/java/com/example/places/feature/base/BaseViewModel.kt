package com.example.places.feature.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Failure
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _baseState = MutableStateFlow<UIBaseState>(UIBaseState.IDLE)
    val baseState = _baseState.asStateFlow()

    private val _newIsLoading = MutableStateFlow(false)
    val newIsLoading = _newIsLoading.asStateFlow()

    private val _noInternet = MutableStateFlow(false)
    val noInternet = _noInternet.asStateFlow()

    protected fun handleUseCaseFailureFromBase(failure: Failure){
        when(failure) {
            is Failure.UnauthorizedOrForbidden -> logError("Log Out")
            is Failure.None -> logError("None")
            is Failure.NetworkConnectionLostSuddenly -> logError("Connection lost suddenly. Check the wifi or mobile data.")
            is Failure.NoNetworkDetected -> {
                showLoading(false)
                _noInternet.update { true }
            }
            is Failure.SSLError -> logError("WARNING: SSL Exception")
            is Failure.TimeOut -> {
                showLoading(false)
                onHandleTimeOut(true)
            }
            is Failure.ServerBodyError -> logError(failure.message)
            is Failure.DataToDomainMapperFailure -> logError("Data to domain mapper failure: ${failure.mapperException}")
            is Failure.ServiceUncaughtFailure -> logError(failure.uncaughtFailureMessage)
        }
        showLoading(false)
    }

    protected fun showLoading(status: Boolean){
        _baseState.update {
            UIBaseState.OnLoading(status)
        }
    }

    private fun onHandleTimeOut(show: Boolean){
        _baseState.update {
            UIBaseState.OnTimeExpired(show)
        }

    }

    private fun logError(errorMessage: String?) {
        Log.e(this.javaClass.simpleName, errorMessage?:"error message is null.")
    }
}