@file:OptIn(ExperimentalMaterial3Api::class)

package com.comitr.github.screens



import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comitr.github.R
import com.comitr.github.sharedPreferances.PrefManager
import com.comitr.github.viewmodel.GithubViewModel


@Composable
fun SettingsScreen() {

    val localContext=LocalContext.current
    var userName by remember { mutableStateOf("${PrefManager(localContext).getData("UserName")}") }
    var gitToken by remember { mutableStateOf("${PrefManager(localContext).getData("GithubToken")}") }


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp, start = 8.dp, end = 8.dp)
        .requiredHeight(620.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .background(Color(0xff1f1f1f))
    )
    {
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
            Text(text = "Github Username",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=180.dp))
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
                PrefManager(localContext).saveData(key="UserName", value = userName)
                Toast.makeText(localContext,"Username Set successfully",Toast.LENGTH_SHORT).show()
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

        Column (
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .background(Color.Black))
        {
            Text(text = "Github Token Key",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=180.dp))
            TextField(value = gitToken,
                onValueChange = {gitToken=it},
                label = { Text(text = "Enter the key....")},
                colors =  TextFieldDefaults.colors(focusedTextColor = Color.Black),
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(70.dp))
                    .background(color = Color(0xffa7a7a7))
            )
            Button(onClick = {
                if(gitToken.length==40){
                    PrefManager(localContext).saveData(key="GithubToken", value = gitToken)
                    Toast.makeText(localContext,"Token Set successfully",Toast.LENGTH_SHORT).show()
                    Log.d("${gitToken.length}",gitToken)
                }
                else{
                    Toast.makeText(localContext,"Enter Correct Token",Toast.LENGTH_SHORT).show()
                    Log.d("${gitToken.length}",gitToken)
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

        Column (
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .background(Color.Black))
        {
            Text(text = "Theme",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=180.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = false, onClick = {  }, colors = RadioButtonDefaults.colors(
                    Color.White))
                Text(text = "Light Mode", color = Color.White)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = true, onClick = {  }, colors = RadioButtonDefaults.colors(
                    Color.White))
                Text(text = "Dark Mode", color = Color.White)
            }

        }
        Column (
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .background(Color.Black))
        {
            Text(text = "About",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )
            Divider(color = Color.White, modifier = Modifier.padding(start=8.dp,end=180.dp))
            Text(
                text = "Made a passion project while being frustrated by limitations of \nGithub on Android. \n ",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF)),
                modifier=Modifier.padding(start=20.dp, top=5.dp)
            )
        }
    }
}


@Composable
fun FinalSettingsScreen(githubViewModel: GithubViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        TopAppBar(imageId1 = R.drawable.logopit_170, imageId2 = R.drawable.vector,githubViewModel)
        SettingsScreen()
    }
}

@Preview
@Composable
fun testPreview2(){
    SettingsScreen()
}