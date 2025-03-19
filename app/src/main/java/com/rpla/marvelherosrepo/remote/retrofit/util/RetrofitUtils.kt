package com.rpla.marvelherosrepo.remote.retrofit.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.rpla.marvelherosrepo.remote.ApiConstants.PRIVATE_KEY
import com.rpla.marvelherosrepo.remote.ApiConstants.PUBLIC_KEY
import java.security.MessageDigest

object RetrofitUtils {
    private const val PREFS_NAME = "secure_prefs"

    fun providePublicKey(context: Context): String? {
        val prefs = getEncryptedPrefs(context)
        return prefs.getString(PUBLIC_KEY, null)
    }

    fun providePrivateKey(context: Context): String? {
        val prefs = getEncryptedPrefs(context)
        return prefs.getString(PRIVATE_KEY, null)
    }

    fun generateMD5Hash(ts: String = "", pvtKey: String, pubKey: String): String {
        val input = ts + pvtKey + pubKey
        val md = MessageDigest.getInstance("MD5")
        val hashBytes = md.digest(input.toByteArray())

        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    private fun getEncryptedPrefs(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}
