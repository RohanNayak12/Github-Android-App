package com.comitr.github.network

import kotlinx.serialization.Serializable


@Serializable
data class FetchRepo(
    val name:String,
    val description: String?,
    val created_at:String,
    val language: String?,
)

@Serializable
data class CreateRepo(
    val name:String,
    val description:String
)


@Serializable
data class FileCommit(
    val message: String,
    val content: String
)
