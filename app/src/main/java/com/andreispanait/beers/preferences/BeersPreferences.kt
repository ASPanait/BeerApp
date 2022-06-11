package com.andreispanait.beers.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersPreferences @Inject constructor(context: Context) :
    SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        private const val PREFERENCES_NAME = "beers_preferences"
        private const val CURRENT_USER = "current_user"
    }

    private val encryptedSharedPreference: SharedPreferences by lazy {
        val masterKeyAlias =
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFERENCES_NAME,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        sharedPreferences
    }

    suspend fun saveUser(username: String) = withContext(Dispatchers.IO) {
        encryptedSharedPreference.edit().putString(CURRENT_USER, username).apply()
    }

    suspend fun deleteUser() = withContext(Dispatchers.IO) {
        encryptedSharedPreference.edit().remove(CURRENT_USER).apply()
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {

    }

}