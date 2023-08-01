package com.example.core.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.core.BuildConfig
import com.example.core.pref.EncryptedPreferences.Companion.BASE_URL
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EncryptedPreferences
@Inject constructor(
    @ApplicationContext context: Context
) {
    private val sp : SharedPreferences by lazy {
        context.getSharedPreferences(SECURE_PREF_NAME, Context.MODE_PRIVATE)
    }

    private val spe : SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clear() {
        spe.clear().apply()
    }

    var encryptedToken: String
        get() = sp.getString(SECURE_SP_TOKEN, "") ?: ""
        set(value) = spe.putString(SECURE_SP_TOKEN, value).apply()

    companion object {
        private const val SECURE_PREF_NAME = "com.example.promoinformation"
        private const val SECURE_SP_TOKEN = "pref_token"
        private const val BASE_URL = "base_url"
    }

}