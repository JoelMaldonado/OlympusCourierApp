package com.jjmf.android.olympuscourierapp.data.server.dto

data class Wrapper<T>(
    val isSuccess: Boolean,
    val mensaje: String,
    val data: T?,
)
