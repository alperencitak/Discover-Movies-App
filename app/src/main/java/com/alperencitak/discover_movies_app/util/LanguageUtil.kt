package com.alperencitak.discover_movies_app.util

import android.content.Context
import java.util.Locale

object LanguageUtil {
    fun getAppLanguage(context: Context): String {
        val locale: Locale = context.resources.configuration.locales[0]
        return if (locale.language == "tr") "tr-TR" else "en-US"
    }
}