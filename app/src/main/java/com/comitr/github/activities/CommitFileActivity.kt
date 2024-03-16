package com.comitr.github.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.comitr.github.R
import com.comitr.github.screens.CommitFile
import com.comitr.github.screens.FinalBrowseRepo
import com.comitr.github.screens.FinalCommitFile
import com.comitr.github.screens.TopAppBar
import com.comitr.github.viewmodel.GithubViewModel
import com.comitr.github.viewmodel.commitFileScreen
import com.comitr.github.viewmodel.homeScreen

class CommitFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm=GithubViewModel(LocalContext.current, rememberNavController())
            vm.Nav(vm, commitFileScreen.route)
        }
    }
    companion object{
        fun start(context: Context){
            val intent= Intent(context, CommitFileActivity::class.java)
            ContextCompat.startActivity(context, intent, Bundle.EMPTY)
        }
    }
}