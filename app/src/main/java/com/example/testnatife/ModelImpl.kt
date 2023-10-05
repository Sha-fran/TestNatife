package com.example.testnatife

import retrofit2.Response

object ModelImpl {
    private val api = ApiClient.client.create(ApiInterface::class.java)

    suspend fun getList():Response<DataResponse> = api.getGif()
}
