package com.example.places.feature.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Failure
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _baseState = MutableSharedFlow<UIBaseState>()
    val baseState = _baseState.asSharedFlow()

    private val _newIsLoading = MutableSharedFlow<Boolean>()
    val newIsLoading = _newIsLoading.asSharedFlow()

    private val _noInternet = MutableStateFlow(false)
    val noInternet = _noInternet.asStateFlow()

    protected fun handleUseCaseFailureFromBase(failure: Failure){
        when(failure) {
            is Failure.UnauthorizedOrForbidden -> logError("Log Out")
            is Failure.None -> logError("None")
            is Failure.NetworkConnectionLostSuddenly -> logError("Connection lost suddenly. Check the wifi or mobile data.")
            is Failure.NoNetworkDetected -> {
                showLoading(false)
                _noInternet.value = false
                _noInternet.value = true
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

    protected fun showLoading (status: Boolean){
        viewModelScope.launch {
            //_newIsLoading.emit(status)
            _baseState.emit(UIBaseState.OnLoading(status))
        }
        onHandleTimeOut(false)
    }

    private fun onHandleTimeOut(show: Boolean){
        viewModelScope.launch {
            _baseState.emit(UIBaseState.OnTimeExpired(show))
        }
    }

    private fun logError(errorMessage: String?) {
        Log.e(this.javaClass.simpleName, errorMessage?:"error message is null.")
    }
}