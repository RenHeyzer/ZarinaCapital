package com.ren.menu.internal.presentation.ui.setting.preference

import android.content.Context
import java.util.Locale

class SharedPreferenceLanguages(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(SHARE_KEY, Context.MODE_PRIVATE)

    fun saveLanguage(language: String) {
        sharedPreferences.edit()
            .putString(LANGUAGE_KEY, language).apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(LANGUAGE_KEY, Locale.getDefault().language) ?: ""
    }

    companion object {
        private const val LANGUAGE_KEY = "language"
        private const val SHARE_KEY = "LanguagePrefs"
    }
}