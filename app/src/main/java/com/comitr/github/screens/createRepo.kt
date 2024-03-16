@file:OptIn(InternalAPI::class)

package com.comitr.github.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comitr.github.R
import com.comitr.github.network.RepoFunctions
import com.comitr.github.network.makeToast
import com.comitr.github.sharedPreferances.PrefManager
import com.comitr.github.viewmodel.GithubViewModel
import io.ktor.util.InternalAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun CreateRepo(){

    var repoName by remember{ mutableStateOf("") }
    var repoDesc by remember{ mutableStateOf("") }
    var resCode by remember{ mutableStateOf(false) }
    val localContext= LocalContext.current
    var gitToken by remember { mutableStateOf("${PrefManager(localContext).getData("GithubToken")}") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, start = 8.dp, end = 8.dp)
            .requiredHeight(540.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(0xff1f1f1f))
    ){
        Text(text = "Create Repository",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp, start = 15.dp)
        )
        Divider(color = Color.White,
            thickness = 2.dp,
            modifier = Modifier.padding(start = 10.dp,end=10.dp)
        )

        Column (
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .background(Color.Black))
        {
            Text(text = "Repository Name",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )

            TextField(value = repoName,
                onValueChange = {repoName=it},
                label = {
                    Text(text = "Enter repo name....",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                        },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(70.dp))
                    .background(color = Color(0xffa7a7a7))
            )

            Text(text = "Repository Description",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )

            TextField(value = repoDesc,
                onValueChange = {repoDesc=it},
                label = {
                    Text(text = "Enter description....",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.CenterHorizontally)
                    .requiredHeight(260.dp)
                    .clip(shape = RoundedCornerShape(17.dp))
                    .background(color = Color(0xffa7a7a7))
            )
            Button(onClick = {
                             try {
                                 runBlocking { async { launch {
                                     val request= RepoFunctions().createRepo(gitToken,repoName,repoDesc)
                                     Log.d("Status","$request")
                                     resCode=request
                                     makeToast(localContext,resCode,"Successfully created repo!!","Failed to create repo!!")
                                 } } }
                             }catch (e:Exception){
                                 Log.d("Backend Error","$e")
                                 makeToast(localContext,resCode,"","Error occurred in network or backend!!")
                             }
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff39ad7c)),
                modifier = Modifier
                    .padding(5.dp)
                    .requiredWidth(width = 87.dp)
                    .requiredHeight(height = 27.dp)
                    .align(Alignment.End)
            )
            { Text("Confirm", fontSize = 10.sp) }

        }
    }
}

@Composable
fun FinalCreateRepo(githubViewModel: GithubViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        TopAppBar(imageId1 = R.drawable.logopit_170, imageId2 = R.drawable.vector,githubViewModel)
        CreateRepo()
    }
}

@Preview
@Composable
fun TestPreview(){
    CreateRepo()
}