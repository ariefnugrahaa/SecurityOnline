package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListKabupatenModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List KabKota Success
) {
    data class ResponseData(
        @SerializedName("IDKabKota")
        var iDKabKota: Float, // 9435
        @SerializedName("Nama")
        var nama: String // KABUPATEN INTAN JAYA
    )
}