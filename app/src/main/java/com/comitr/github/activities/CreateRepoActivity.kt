package com.comitr.github.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.comitr.github.viewmodel.GithubViewModel
import com.comitr.github.viewmodel.createRepoScreen

class CreateRepoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm=GithubViewModel(LocalContext.current, rememberNavController())
            vm.Nav(vm, createRepoScreen.route)
        }
    }
    companion object {
            fun start(context: Context) {
                val intent = Intent(context, CreateRepoActivity::class.java)
                ContextCompat.startActivity(context, intent, Bundle.EMPTY)
            }
        }
}