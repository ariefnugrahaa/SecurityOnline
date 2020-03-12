package com.example.arief.securityonline.network.model


import com.google.gson.annotations.SerializedName

data class ListFollowUp(
    val acknowledge: Boolean?,
    val responseCode: String?,
    val responseData: List<ResponseData>,
    val responseMessage: String?
) {
    data class ResponseData(
        @SerializedName("Createdby")
        val createdby: String?,
        @SerializedName("Date")
        val date: String?,
        @SerializedName("ID")
        val iD: Int?,
        @SerializedName("IDLaporan")
        val iDLaporan: String?,
        @SerializedName("Kerugian")
        val kerugian: String?,
        @SerializedName("Tindakan")
        val tindakan: String?
    )
}