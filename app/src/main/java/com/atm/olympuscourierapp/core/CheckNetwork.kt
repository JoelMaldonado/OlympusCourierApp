package com.atm.olympuscourierapp.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class CheckNetwork(private val cm:ConnectivityManager) : LiveData<Boolean>() {
    constructor(context: Context) : this(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    private val networkCall = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(builder.build(), networkCall)
    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(networkCall)
    }
}