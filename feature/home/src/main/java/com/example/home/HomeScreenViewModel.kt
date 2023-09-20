package com.example.home

import androidx.lifecycle.ViewModel
import com.example.designsystem.navigation.AppNavigator
import com.example.designsystem.navigation.WeatherDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onNavigateToSearch() {
        appNavigator.tryNavigationTo(WeatherDestinations.SearchScreen.route)
    }

}