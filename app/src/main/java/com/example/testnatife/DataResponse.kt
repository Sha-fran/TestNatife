package com.example.testnatife

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data") var data: List<Data>
)

data class Data(
    @SerializedName("images") var images:Images
)

data class Images(
    @SerializedName("original") var original:Original
)

data class Original(
    @SerializedName("url") var url:String
)
