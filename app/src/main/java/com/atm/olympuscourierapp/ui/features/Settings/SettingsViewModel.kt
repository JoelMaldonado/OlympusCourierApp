package com.atm.olympuscourierapp.ui.features.Settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel() {

    val isSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)


    fun deleteAccount() {
        viewModelScope.launch {
            isLoading.value = true
            delay(3000)
            isLoading.value = false
            isSuccess.value = true
        }
    }
}