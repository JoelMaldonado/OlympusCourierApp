package com.jjmf.android.olympuscourierapp.ui.features.Menu

import androidx.lifecycle.ViewModel
import com.atm.olympuscourierapp.domain.repository.RepartoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: RepartoRepository,
) : ViewModel() {

}