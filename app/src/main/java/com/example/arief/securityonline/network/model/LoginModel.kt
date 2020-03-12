package com.pertamina.pdsi.securityonline.Model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginModel(
    @Expose
    @SerializedName("acknowledge")
    var acknowledge: Boolean, // true
    @Expose
    @SerializedName("responseCode")
    var responseCode: Int, // 0
    @Expose
    @SerializedName("responseData")
    var responseData: ResponseData?,
    @Expose
    @SerializedName("responseMessage")
    var responseMessage: String // Login Success
) {
    data class ResponseData(
        @Expose
        @SerializedName("email")
        var email: String, // user1@cobacoba.com
        @Expose
        @SerializedName("name")
        var name: String, // User Pertama
        @Expose
        @SerializedName("pernr")
        var pernr: String, // 0
        @Expose
        @SerializedName("token")
        var token: String, // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwZXJuciI6IjAiLCJuYW1lIjoiVXNlciBQZXJ0YW1hIiwidXNlcm5hbWUiOiJhZG1pbjEiLCJlbWFpbCI6InVzZXIxQGNvYmFjb2JhLmNvbSIsInRpcGUiOiIiLCJ2YWxpZGF0b3IiOiJIZHloRWVZdWZ3U2ZnNWR1S3d1MndPSXBOS3hHWTBLNCIsImp0aSI6IjJjZDU2NzUwLTlmNjMtNGNhYi04NjgxLTBmYTRjNzcyMDIyZiIsImlhdCI6MTU3MjMzODk5NCwiZXhwIjoxNTcyOTQzNzk0fQ.MmEFoUgCynjAvnFftmfcqvziqFWQnobaRkO_btOYnB8
        @Expose
        @SerializedName("username")
        var username: String, // admin1
        @Expose
        @SerializedName("role")
        var role: String // p
    )
}