package com.comitr.github.network

import com.comitr.github.network.ktorClient.json
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@InternalAPI
class RepoFunctions {
    suspend fun fetchRepo(userName:String):Array<FetchRepo>{
        val client=ktorClient
        val request=client.httpClient.get(urlString = "https://api.github.com/users/${userName}/repos").bodyAsText()
        val reqArray=json.decodeFromString<Array<FetchRepo>>(request)
        //client.httpClient.close()
        return reqArray
    }
    suspend fun createRepo(userToken:String,newRepoName:String,newRepoDescription: String=""):Boolean{
        val client=ktorClient

        val text:String="""
        {
        "name": "$newRepoName",
        "description": "$newRepoDescription"
        }
    """

        val repoSerializable=Json.decodeFromString<CreateRepo>(text)
        val requestBody:String= Json.encodeToString(repoSerializable)
        val  url="https://api.github.com/user/repos"
        val request=client.httpClient.post{
            url("${url}")
            headers {
                append("Authorization","Bearer $userToken")
            }
            body=TextContent(requestBody, ContentType.Application.Json)

        }
        //Log.d("Response :" ,"$request")
        var bool=false
        if (request.status== HttpStatusCode.Created){bool=true}
        //client.httpClient.close()
        return bool
    }

    suspend fun createFile(
        owner: String,
        userToken: String,
        RepoName: String,
        path: String,
        message: String,
        content:String
    ):Boolean{
        val client=ktorClient

        val text:String="""
        {
        "message": "$message",
        "content":"$content"
        }
    """
        val repoSerializable=Json.decodeFromString<FileCommit>(text)
        val requestBody:String= Json.encodeToString(repoSerializable)

        val request=client.httpClient.put {
            url("https://api.github.com/repos/${owner}/${RepoName}/contents/${path}")
            headers {
                append("Authorization","Bearer $userToken")
            }
            body=TextContent(requestBody, ContentType.Application.Json)
        }
        //Log.d("Response :" ,"$request")
        var bool=false
        if (request.status== HttpStatusCode.Created){bool=true}
        //client.httpClient.close()
        return bool
    }

}


