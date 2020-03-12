package com.example.arief.securityonline.network.model


data class TakeQuestModel(
    val acknowledge: Boolean?,
    val responseCode: String?,
    val responseData: ResponseData?,
    val responseMessage: String?
) {
    class ResponseData(
    )
}