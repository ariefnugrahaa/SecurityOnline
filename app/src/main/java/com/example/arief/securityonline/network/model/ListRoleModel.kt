package com.example.arief.securityonline.network.model


import com.google.gson.annotations.SerializedName

data class ListRoleModel(
    val acknowledge: Boolean?,
    val responseCode: String?,
    val responseData: List<ResponseData>?,
    val responseMessage: String?
) {
    data class ResponseData(
        @SerializedName("Email")
        val email: String?,
        @SerializedName("GToken")
        val gToken: String?,
        @SerializedName("ID")
        val iD: Int?,
        @SerializedName("Jabatan")
        val jabatan: String?,
        @SerializedName("LokasiStay")
        val lokasiStay: String?,
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Nopek")
        val nopek: String?,
        @SerializedName("Password")
        val password: String?,
        @SerializedName("Perusahaan")
        val perusahaan: String?,
        @SerializedName("Role")
        val role: String?,
        @SerializedName("Source")
        val source: String?,
        @SerializedName("StatusUser")
        val statusUser: String?,
        @SerializedName("TanggalRegister")
        val tanggalRegister: String?,
        @SerializedName("TanggalUpdate")
        val tanggalUpdate: String?,
        @SerializedName("UserID")
        val userID: String?,
        @SerializedName("Validator")
        val validator: String?
    )
}