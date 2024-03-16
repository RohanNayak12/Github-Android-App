package com.comitr.github.repo

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import java.io.BufferedReader
import java.io.InputStreamReader

fun readTextFromUri(uri: Uri, context: Context):String{
    val stringBuilder:StringBuilder =StringBuilder()
    context.contentResolver.openInputStream(uri).use {inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            var line:String? = reader.readLine()
            while (line!=null){
                stringBuilder.append(line)
                line=reader.readLine()
            }
        }
    }
    return stringBuilder.toString()
}

fun encodeFromContent(content:String):String{
    val encoded=java.util.Base64.getEncoder().encodeToString(content.toByteArray())
    return encoded
}

