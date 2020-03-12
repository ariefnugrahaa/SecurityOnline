package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class ListMotifModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>?,
    @SerializedName("responseMessage")
    var responseMessage: String // Get List Category Success
) {
    data class ResponseData(
        @SerializedName("Data")
        var `data`: String, // Terorisme
        @SerializedName("IDKategori")
        var iDKategori: Int, // 1
        @SerializedName("IDMotif")
        var iDMotif: Int, // 10
        @SerializedName("KategoriMotif")
        var kategoriMotif: String // 10
    )
}