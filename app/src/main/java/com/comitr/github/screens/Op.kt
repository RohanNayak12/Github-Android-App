package com.comitr.github.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comitr.github.R
import com.comitr.github.activities.BrowseRepoActivity
import com.comitr.github.activities.CommitFileActivity
import com.comitr.github.activities.CreateRepoActivity
import com.comitr.github.viewmodel.GithubViewModel

@Composable
fun OperationScreen(browse:Int,create:Int,commit:Int){
    val context= LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, start = 8.dp, end = 8.dp)
            .requiredHeight(550.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(0xff1f1f1f))
    ){
        Text(text = "Select Operation",
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
            Text(text = "Repositories",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=235.dp))
            Button(onClick = { BrowseRepoActivity.start(context) },
                colors = ButtonDefaults.buttonColors(Color(0xFFA7A7A7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp))
            {
                Image(painter = painterResource(id = browse), contentDescription = "",
                    Modifier
                        .padding(end = 30.dp)
                        .size(50.dp))
                Text(text="Browse Repositories", color = Color.Black)
            }
            Button(onClick = { CreateRepoActivity.start(context) },
                colors = ButtonDefaults.buttonColors(Color(0xFFA7A7A7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp))
            {
                Image(painter = painterResource(id = create), contentDescription = "",
                    Modifier
                        .padding(end = 40.dp)
                        .size(50.dp))
                Text(text="Create Repository", color = Color.Black)
            }
        }
        Column (
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .background(Color.Black))
        {
            Text(text = "Files",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=300.dp))
            Button(onClick = { CommitFileActivity.start(context) },
                colors = ButtonDefaults.buttonColors(Color(0xFFA7A7A7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp))
            {
                Image(painter = painterResource(id = commit), contentDescription = "",
                    Modifier
                        .padding(end = 70.dp)
                        .size(50.dp))
                Text(text="Commit File", color = Color.Black)
            }

        }
    }
    Text(
        text = "Made by Harsh & Rohan ",
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace),
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        modifier = Modifier.padding(top = 130.dp, start = 100.dp)
        )
}

@Composable
fun FinalHomeScreen(githubViewModel: GithubViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        TopAppBar(imageId1 = R.drawable.logopit_170, imageId2 = R.drawable.vector,githubViewModel)
        OperationScreen(R.drawable.toppng1, R.drawable.create1, R.drawable.fileupload1)
    }
}