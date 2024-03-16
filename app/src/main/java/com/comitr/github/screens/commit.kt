@file:OptIn(InternalAPI::class)

package com.comitr.github.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.comitr.github.repo.encodeFromContent
import com.comitr.github.repo.readTextFromUri
import com.comitr.github.sharedPreferances.PrefManager
import com.comitr.github.viewmodel.GithubViewModel
import io.ktor.util.InternalAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@SuppressLint("Range")
@Composable
fun CommitFile(){
    var fileName by remember { mutableStateOf("") }
    var repoName by remember { mutableStateOf("") }
    var commitMessage by remember { mutableStateOf("") }
    var fileContent by remember { mutableStateOf("") }
    var encodedFileTxt by remember { mutableStateOf("") }
    val context= LocalContext.current
    var resCode by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("${PrefManager(context).getData("UserName")}") }
    var gitToken by remember { mutableStateOf("${PrefManager(context).getData("GithubToken")}") }


    val launcher= rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = { uri: Uri? ->
        if (uri!!.scheme.equals("content")){
            val cursor=context.contentResolver.query(uri,null,null,null,null)
            try {
                if (cursor!=null && cursor.moveToFirst()){
                    fileName=cursor!!.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            }
            catch (e:Exception){
                Toast.makeText(context,"Error creating the File", Toast.LENGTH_LONG).show()}
            finally { cursor?.close()}
            fileContent= readTextFromUri(uri,context)
            encodedFileTxt= encodeFromContent(fileContent)
        }
    })



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, start = 8.dp, end = 8.dp)
            .requiredHeight(460.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(0xff1f1f1f))
    ){
        Text(text = "Commit File",
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
            Text(text = "Upload File",
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

            Button(onClick = { launcher.launch("*/*") },
                colors = ButtonDefaults.buttonColors(Color(0xFFA7A7A7)),
                modifier = Modifier.padding(start = 15.dp)
            )
            { Text(text = "Select File", color = Color.Black) }
            Text(text = "Commit Message",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start=10.dp,top=9.dp)
            )

            TextField(value = commitMessage,
                onValueChange = {commitMessage=it},
                label = {
                    Text(text = "Enter message....", color = Color.Black)
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.CenterHorizontally)
                    .requiredHeight(160.dp)
                    .clip(shape = RoundedCornerShape(17.dp))
                    .background(color = Color(0xffa7a7a7))
            )
            Button(onClick = {
                             try {
                                 runBlocking { async { launch {
                                     val request=RepoFunctions().createFile(
                                         userName,gitToken,repoName,fileName,commitMessage,encodedFileTxt
                                     )
                                     Log.d("Status", "$request")
                                     resCode = request
                                     makeToast(context,resCode,"Successfully committed!!","Failed to commit files!!")
                                 } } }
                             }catch (e:Exception){
                                 Log.d("Network Error", "$e")
                                 makeToast(context,resCode,"","Error occurred in file or networking!!")
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
fun FinalCommitFile(githubViewModel: GithubViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        TopAppBar(imageId1 = R.drawable.logopit_170, imageId2 = R.drawable.vector,githubViewModel)
        CommitFile()
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCommit(){
    CommitFile()
}