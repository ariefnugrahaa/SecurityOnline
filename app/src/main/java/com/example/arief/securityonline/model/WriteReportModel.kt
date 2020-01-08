package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class WriteReportModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: ResponseData,
    @SerializedName("responseMessage")
    var responseMessage: String // Post Report Success
) {
    class ResponseData(
    )
}