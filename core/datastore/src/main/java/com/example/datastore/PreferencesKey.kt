package com.example.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKey {

    val news_token = stringPreferencesKey("news_api_key")
    val weather_token = stringPreferencesKey("weather_api_key")

}