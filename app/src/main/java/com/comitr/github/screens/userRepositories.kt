package com.comitr.github.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun UserRepos(repoName:String,description:String?,date:String,language: String?){
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(27))
            .background(Color(0xffA7A7A7))
            .clickable(onClick = {})
    ) {

        val datePattern=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val localdate=LocalDateTime.parse(date,datePattern).toLocalDate()

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp) ,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween){
            Column() {
                Text(text = "$repoName", fontWeight = FontWeight.ExtraBold)
                Divider(thickness = 1.dp, color = Color.Black, modifier = Modifier.padding(top = 5.dp).requiredWidth(200.dp))
            }
            Card (colors = CardDefaults.cardColors(Color.Black)){
                Text(text = "Created on $localdate",
                    color= Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(end=5.dp))
            }
        }
        Text(text = "$description", modifier = Modifier.padding(end=50.dp))
        Card(
            colors = CardDefaults.cardColors(Color(0xff39AD7C)),
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 15.dp)
        )
        { Text(text = "$language") }
    }
}

@Preview(showBackground=true)
@Composable
fun UserRepoPrev(){
    UserRepos(repoName = "Repo", description = "Created for fun", date = "2023-10-05T10:31:05Z", language = "Kotlin")
}
