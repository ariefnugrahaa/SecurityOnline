package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: ResponseData,
    @SerializedName("responseMessage")
    var responseMessage: String // Get User Controller Success
) {
    data class ResponseData(
        @SerializedName("Email")
        var email: String, // user1@cobacoba.com
        @SerializedName("GToken")
        var gToken: String, // yuhuuuuu
        @SerializedName("ID")
        var iD: Int, // 0
        @SerializedName("Jabatan")
        var jabatan: String, // 0
        @SerializedName("LokasiStay")
        var lokasiStay: String, // malang
        @SerializedName("Name")
        var name: String, // User Pertama
        @SerializedName("Nopek")
        var nopek: String, // 0
        @SerializedName("Password")
        var password: String, // 415676a8a1dcace5edc39b4625cd5b31eb19632feead8f1aeb4d591dc3fa0b3ee8b225d89690353f8b24008c96fea40cf81eb77850e401ccc962f731615075e3
        @SerializedName("Perusahaan")
        var perusahaan: String, // 0
        @SerializedName("Role")
        var role: String,
        @SerializedName("Source")
        var source: String, // system
        @SerializedName("StatusUser")
        var statusUser: String, // A
        @SerializedName("TanggalRegister")
        var tanggalRegister: String, // 2019-11-03T19:03:52.000Z
        @SerializedName("TanggalUpdate")
        var tanggalUpdate: String, // 0000-00-00 00:00:00
        @SerializedName("UserID")
        var userID: String, // admin12
        @SerializedName("Validator")
        var validator: String // v6r5fZ9dE53p3sDXOUgWO0pWx1Gax5z4
    )
}