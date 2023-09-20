package com.example.testnatife

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v2/emoji?")
    fun getGif(
        @Query("api_key") apiKey:String
    ):Single<MutableList<Data>>
}
