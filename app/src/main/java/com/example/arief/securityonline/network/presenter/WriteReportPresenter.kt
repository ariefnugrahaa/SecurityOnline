package com.example.arief.securityonline.network.presenter

import RestApi
import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.extension.sharedPreferenceUser
import com.example.arief.securityonline.presentation.home.MainActivity
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.fileImage1
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.fileImage2
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.fileImage3
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idCatatan
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idCategory
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idKabupaten
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idKecamatan
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idMotif
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idPeristiwa
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idProject
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idProvinsi
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idRig
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idStatusRig
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idSumber
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idSumur
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport.WriteReportFragment.Companion.idWilayah
import com.pertamina.pdsi.securityonline.Model.WriteReportModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Response

class WriteReportPresenter (iViewWriteReport: BaseInterface.IWritePresenter){

    private val iWriteReport = iViewWriteReport

    private fun uploadImage(context: Context):MutableList<MultipartBody.Part> {

        val imageMap:MutableList<MultipartBody.Part>? = mutableListOf()

            if (fileImage1 != null) imageMap?.add(MultipartBody.Part.createFormData(context.sharedPreferenceUser("id").toString(), fileImage1?.name, fileImage1!!.asRequestBody("image/*".toMediaTypeOrNull())))

            if (fileImage2 != null) imageMap?.add(MultipartBody.Part.createFormData(context.sharedPreferenceUser("id").toString(), fileImage2?.name, fileImage2!!.asRequestBody("image/*".toMediaTypeOrNull())))

            if (fileImage3 != null) imageMap?.add(MultipartBody.Part.createFormData(context.sharedPreferenceUser("id").toString(), fileImage3?.name, fileImage3!!.asRequestBody("image/*".toMediaTypeOrNull()))
            )
            return imageMap!!
        }

    private fun uploadSpinner():MutableMap<String, RequestBody> {

        val map: HashMap<String, RequestBody> = HashMap()
        map["id_kategori"] = idCategory.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_motif"] = idMotif.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_project"] = idProject.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_rig"] = idRig.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_status_rig"] = idStatusRig.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_propinsi"] = idProvinsi.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_kab_kota"] = idKabupaten.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_kecamatan"] = idKecamatan.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["id_wilayah"] = idWilayah.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["lokasi_sumur"] = idSumur.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["sumber"] = idSumber.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["peristiwa"] = idPeristiwa.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["catatan"] = idCatatan.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["latitude"] = MainActivity.latitude.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        map["longitude"] = MainActivity.longitude.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())

        return map
    }

    fun postWriteReport(context: Context){
        RestApi.create(context).postWrite(uploadImage(context), uploadSpinner()).enqueue(object : retrofit2.Callback<WriteReportModel>{
            override fun onFailure(call: Call<WriteReportModel>, t: Throwable) {
                iWriteReport.onDataErrorReport(t)
            }

            override fun onResponse(call: Call<WriteReportModel>, response: Response<WriteReportModel>) {
                iWriteReport.onDataCompleteWriteReport(response.body() as WriteReportModel)
            }
        })
    }
}