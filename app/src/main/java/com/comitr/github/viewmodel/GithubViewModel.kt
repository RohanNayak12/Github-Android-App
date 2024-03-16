package com.comitr.github.viewmodel

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.comitr.github.screens.FinalBrowseRepo
import com.comitr.github.screens.FinalCommitFile
import com.comitr.github.screens.FinalCreateRepo
import com.comitr.github.screens.FinalHomeScreen
import com.comitr.github.screens.FinalSettingsScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GithubViewModel constructor(pContext:Context,pNavController: NavHostController):ViewModel() {


    private var context:Context
    private var navController:NavHostController
    private var token :String =""
    val uiState= MutableStateFlow(GithubUiState(pContext,pNavController))
    val currentUiState : StateFlow<GithubUiState> =uiState.asStateFlow()

    fun getter():GithubUiState{ return currentUiState.value }
    fun setter(context: Context,navHostController: NavHostController){ uiState.value=GithubUiState(context,navHostController) }
    //fun tokenSetter(githubToken:String){}

    init {
        context=pContext
        navController=pNavController
        setter(pContext,pNavController)
    }




    @Composable
    fun Nav(githubViewModel: GithubViewModel,startDestination:String){
        //val githubViewModel=GithubViewModel(LocalContext.current, rememberNavController())
        val uiState=githubViewModel.getter()
        val navController=uiState.navController
        NavHost(navController = navController, startDestination = startDestination){
            composable(homeScreen.route){ FinalHomeScreen(githubViewModel) }
            composable(settingsScreen.route){ FinalSettingsScreen(githubViewModel) }
            composable(createRepoScreen.route){ FinalCreateRepo(githubViewModel = githubViewModel)}
            composable(browseRepoScreen.route){ FinalBrowseRepo(githubViewModel = githubViewModel)}
            composable(commitFileScreen.route){ FinalCommitFile(githubViewModel = githubViewModel)}
        }
    }
}

interface navDestinations { val route:String }

object homeScreen:navDestinations{ override val route="HomeScreen" }
object settingsScreen:navDestinations{ override val route="SettingsScreen" }
object createRepoScreen:navDestinations{ override val route="CreateRepoScreen" }
object browseRepoScreen:navDestinations{ override val route="BrowseRepoScreen" }
object commitFileScreen:navDestinations{ override val route="CommitFileScreen" }