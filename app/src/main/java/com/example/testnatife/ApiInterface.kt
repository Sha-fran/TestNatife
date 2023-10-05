package com.example.testnatife

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/v2/emoji?api_key=80xeeh9hOqxgQPcSfXE5q4uNiA2QQqeO&limit=20&offset=0")
    suspend fun getGif():Response<DataResponse>
}
