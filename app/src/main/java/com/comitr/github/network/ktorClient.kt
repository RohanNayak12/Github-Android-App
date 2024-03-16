package com.comitr.github.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

object ktorClient {

    val json= Json {
        encodeDefaults=true
        ignoreUnknownKeys=true
        isLenient=true
    }
    val httpClient= HttpClient(CIO){
        expectSuccess=true
        install(Auth){

        }
        install(Logging){
            logger= Logger.DEFAULT
            level= LogLevel.HEADERS
        }

        install(HttpTimeout){
            socketTimeoutMillis=30000
            requestTimeoutMillis=30000
            connectTimeoutMillis=30000
        }
        install(ContentNegotiation){
            Json {
                ignoreUnknownKeys=true
                prettyPrint=true
                isLenient=true
                explicitNulls=false
            }
        }


        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}


