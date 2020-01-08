package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListRigModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List Rig Success
) {
    data class ResponseData(
        @SerializedName("IDRig")
        var iDRig: Int, // 1
        @SerializedName("KodeRig")
        var kodeRig: Int, // 0
        @SerializedName("Lokasi")
        var lokasi: String,
        @SerializedName("NamaRig")
        var namaRig: String // test rig
    )
}