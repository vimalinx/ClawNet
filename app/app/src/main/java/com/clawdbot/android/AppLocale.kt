package com.clawdbot.android

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import java.util.Locale

object AppLocale {
  private const val systemTag = "system"

  fun apply(context: Context, tag: String): Boolean {
    val normalized = tag.trim().ifBlank { systemTag }
    val desired =
      if (normalized == systemTag) {
        LocaleList.getDefault()
      } else {
        LocaleList.forLanguageTags(normalized)
      }
    val resources = context.resources
    val current = resources.configuration.locales
    if (current.toLanguageTags() == desired.toLanguageTags()) return false
    val first = desired[0]
    if (first != null) {
      Locale.setDefault(first)
    }
    val config = Configuration(resources.configuration)
    config.setLocales(desired)
    resources.updateConfiguration(config, resources.displayMetrics)
    return true
  }
}
