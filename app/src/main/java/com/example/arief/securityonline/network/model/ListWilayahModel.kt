package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class ListWilayahModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List Wilayah Success
) {
    data class ResponseData(
        @SerializedName("IDWilayah")
        var iDWilayah: Long, // 3507290009
        @SerializedName("Nama")
        var nama: String // TAWANGARGO
    )
}