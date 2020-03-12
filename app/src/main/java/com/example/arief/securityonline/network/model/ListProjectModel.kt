package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListProjectModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List Project Success
) {
    data class ResponseData(
        @SerializedName("Data")
        var `data`: String, // test project
        @SerializedName("IDProject")
        var IDProject: Int // 1
    )
}