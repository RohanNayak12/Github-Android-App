package com.comitr.github.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comitr.github.activities.MainActivity
import com.comitr.github.viewmodel.GithubViewModel
import com.comitr.github.viewmodel.settingsScreen

@Composable
fun TopAppBar(imageId1:Int,imageId2: Int,githubViewModel: GithubViewModel ){
    //val githubViewModel=GithubViewModel(LocalContext.current, rememberNavController())
    val uiState by githubViewModel.uiState.collectAsState()
    val navController=uiState.navController
    //val context=Intent(uiState.context,MainActivity::class.java)
    
    Log.d("Getter","Value set sccessfully")
    Box(
        Modifier
            .fillMaxWidth()
            .requiredHeight(63.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(Color(0xff1f1f1f))
        //, colors = CardDefaults.cardColors(Color.DarkGray) ,
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(start = 15.dp)
            ,verticalAlignment =Alignment.CenterVertically
            ,horizontalArrangement = Arrangement.Start
        ) {
            Image(painter = painterResource(id= imageId1),contentDescription="",
                Modifier
                    .size(35.dp)
                    .clickable {
                        // navController.navigate(homeScreen.route)
                        MainActivity.start(uiState.context)
                    })
        }
        Row(Modifier.fillMaxSize()
            ,verticalAlignment =Alignment.CenterVertically
            ,horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Commitr.",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.displaySmall,
                fontSize = 35.sp)
        }
        Row(
            Modifier
                .fillMaxSize()
                .padding(end = 15.dp)
            ,verticalAlignment =Alignment.CenterVertically
            ,horizontalArrangement = Arrangement.End) {
            Image(painter = painterResource(id= imageId2),contentDescription="",
                Modifier
                    .size(35.dp)
                    .clickable {
                        //startActivity(context,intent, Bundle.EMPTY)
                        navController.navigate(settingsScreen.route)
                    })
        }
    }
}
/*
@Preview
@Composable
fun Preview2(){
    //TopAppBar(R.drawable.logopit_170,R.drawable.vector)
} */