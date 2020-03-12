package com.example.arief.securityonline.network.model


import com.google.gson.annotations.SerializedName

data class SendQuestModel(
    val acknowledge: Boolean?,
    val responseCode: String?,
    val responseData: ResponseData?,
    val responseMessage: String?
) {
    class ResponseData(
    )
}