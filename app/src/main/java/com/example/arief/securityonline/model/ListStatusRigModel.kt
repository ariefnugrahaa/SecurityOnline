package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListStatusRigModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List Status Rig Success
) {
    data class ResponseData(
        @SerializedName("IDStatusRig")
        var iDStatusRig: Int, // 1
        @SerializedName("Status")
        var status: String // Rusak Parah
    )
}