package com.example.arief.securityonline.network.model


data class PostFollowupModel(
    val acknowledge: Boolean?,
    val responseCode: String?,
    val responseData: ResponseData?,
    val responseMessage: String?
) {
    class ResponseData(
    )
}