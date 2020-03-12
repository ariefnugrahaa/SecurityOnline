package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListKecamatanModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List Kecamatan Success
) {
    data class ResponseData(
        @SerializedName("IDKecamatan")
        var iDKecamatan: Float, // 3507330
        @SerializedName("Nama")
        var nama: String // KASEMBON
    )
}