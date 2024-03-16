@file:OptIn(InternalAPI::class)

package com.comitr.github.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comitr.github.R
import com.comitr.github.network.FetchRepo
import com.comitr.github.network.RepoFunctions
import com.comitr.github.viewmodel.GithubViewModel
import io.ktor.util.InternalAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun BrowseRepo(){

    //val localContext= LocalContext.current
    var userName by remember { mutableStateOf("") }
    var repoNames by remember { mutableStateOf(arrayOf<FetchRepo>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, start = 8.dp, end = 8.dp)
            .requiredHeight(660.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(0xff1f1f1f))
            .verticalScroll(rememberScrollState(), true)
    )
    {
        Text(text = "Browse Repository",
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
            Text(text = "Enter Github Username",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            //Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=180.dp))
            TextField(value = userName,
                onValueChange = {userName=it},
                label = { Text(text = "Enter username....")},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(70.dp))
                    .background(color = Color(0xffa7a7a7))
            )
            Button(onClick = {
                             try {
                                 runBlocking {
                                     async {launch {
                                         repoNames=(RepoFunctions().fetchRepo(userName))
                                     } }
                                 }
                             }catch (e:Exception){Log.d("BrowseRepoException","$e")}
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
        for (repo in repoNames){
            UserRepos(repoName = repo.name, description = repo.description, date = repo.created_at, language = repo.language)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}




@Composable
fun FinalBrowseRepo(githubViewModel: GithubViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        TopAppBar(imageId1 = R.drawable.logopit_170, imageId2 = R.drawable.vector,githubViewModel)
        BrowseRepo()
    }
}

@Preview(showBackground = true)
@Composable
fun BrowsePreview(){
    BrowseRepo()
}
