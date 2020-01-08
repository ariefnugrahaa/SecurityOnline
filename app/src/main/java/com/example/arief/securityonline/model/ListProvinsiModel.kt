package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListProvinsiModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List propinsi Success
) {
    data class ResponseData(
        @SerializedName("IDPropinsi")
        var iDPropinsi: Long, // 9200000000
        @SerializedName("Nama")
        var nama: String // Irian Jaya Barat
    )
}