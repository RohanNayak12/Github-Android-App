package com.comitr.github.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController

data class GithubUiState(
    val context: Context,
    val navController: NavHostController
)
