package com.atm.olympuscourierapp.app

import android.content.Context

class Preferencias(context: Context) {
    private val SHARED_NAME = "MYDTB"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    private val KEY_DOC = "KEY_DOC"
    private val KEY_ID = "KEY_ID"
    private val KEY_RECORDAR = "KEY_RECORDAR"

    private val KEY_TOKEN = "KEY_TOKEN"


    fun saveDoc(valor: String) = storage.edit().putString(KEY_DOC, valor).apply()
    fun getDoc() = storage.getString(KEY_DOC, null)
    fun removeDoc() = storage.edit().remove(KEY_DOC).apply()

    fun saveRecordar(valor: Boolean) = storage.edit().putBoolean(KEY_RECORDAR, valor).apply()
    fun getRecordar() = storage.getBoolean(KEY_RECORDAR, false)

    fun saveToken(valor: String) = storage.edit().putString(KEY_TOKEN, valor).apply()
    fun getToken() = storage.getString(KEY_TOKEN, null)

    fun saveID(valor: Int) = storage.edit().putInt(KEY_ID, valor).apply()
    fun getID() = storage.getInt(KEY_ID, 0)
}