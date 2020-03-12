package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.SerializedName

data class HomeDataModel(
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @SerializedName("responseCode")
    var responseCode: String, // 00
    @SerializedName("responseData")
    var responseData: List<ResponseData>,
    @SerializedName("responseMessage")
    var responseMessage: String // Get Lates Report Success
) {
    data class ResponseData(
        @SerializedName("Catatan")
        var catatan: String, // test
        @SerializedName("Foto")
        var foto: List<String>?,
        @SerializedName("IDLaporan")
        var iDLaporan: Int, // 1
        @SerializedName("IDMotif")
        var iDMotif: Int, // 1
        @SerializedName("IDProject")
        var iDProject: Int, // 1
        @SerializedName("IDRig")
        var iDRig: Int, // 1
        @SerializedName("IDStatusRig")
        var iDStatusRig: Int, // 1
        @SerializedName("IDWilayah")
        var iDWilayah: String, // 1101010012
        @SerializedName("kabupaten")
        var kabupaten: String, // KABUPATEN SIMEULUE
        @SerializedName("kategori")
        var kategori: String, // Security Incident
        @SerializedName("kecamatan")
        var kecamatan: String, // TEUPAH SELATAN
        @SerializedName("Kerugian")
        var kerugian: String, // 50000
        @SerializedName("LokasiSumur")
        var lokasiSumur: String, // test
        @SerializedName("motif")
        var motif: String, // Demontrasi
        @SerializedName("Peristiwa")
        var peristiwa: String, // test
        @SerializedName("project")
        var project: String, // test project
        @SerializedName("propinsi")
        var propinsi: String, // ACEH
        @SerializedName("rig")
        var rig: String, // test rig
        @SerializedName("Status")
        var status: Int, // 1
        @SerializedName("statusLaporan")
        var statusLaporan: String, // Dilaporkan
        @SerializedName("TanggalMasuk")
        var tanggalMasuk: String, // a day ago
        @SerializedName("TanggalUpdate")
        var tanggalUpdate: String, // 0000-00-00 00:00:00
        @SerializedName("Tindakan")
        var tindakan: String, // test
        @SerializedName("UserID")
        var userID: String, // admin12
        @SerializedName("UserUpdate")
        var userUpdate: String,
        @SerializedName("wilayah")
        var wilayah: String, // LABUHAN BAKTI
        @SerializedName("FollowupBy")
        var FollowupBy: String,
        @SerializedName("TakeBy")
        var TakeBy: String,
        @SerializedName("SendTindakan")
        var SendTindakan: String




    )
}