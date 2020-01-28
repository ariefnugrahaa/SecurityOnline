package com.example.arief.securityonline.presenter

import RestApi
import android.content.Context
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.extension.sharedPreferenceUser
import com.example.arief.securityonline.view.bottomnavigation.HomeFragment
import com.example.arief.securityonline.view.bottomnavigation.HomeFragment.Companion.fileImage1
import com.pertamina.pdsi.securityonline.Model.WriteReportModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class WriteReportPresenter (iViewWriteReport: BaseInterface.IWritePresenter){

    private val iWriteReport = iViewWriteReport

        private fun uploadImage(context: Context):MutableList<MultipartBody.Part> {

            val imageMap:MutableList<MultipartBody.Part>? = mutableListOf()

            if(fileImage1 != null) imageMap?.add(MultipartBody.Part.createFormData(context.sharedPreferenceUser("id").toString(), fileImage1?.name, RequestBody.create(
                "image/*".toMediaTypeOrNull(), fileImage1!!)))

//                        "image/*".toMediaTypeOrNull(), HomeFragment.fileImage1!!)
//            if(fileImage1 != null) imageMap?.add(MultipartBody.Part.createFormData(sharedPreference.getValueString("username").toString(), fileImage1?.name, RequestBody.create(
//                MediaType.parse("image/*"), fileImage1!!)))



            if (HomeFragment.fileImage2 != null) imageMap?.add(
                MultipartBody.Part.createFormData(
                    context.sharedPreferenceUser("id").toString(),
                    HomeFragment.fileImage2?.name,
                    RequestBody.create("image/*".toMediaTypeOrNull(), HomeFragment.fileImage2!!)
                )
            )
            if (HomeFragment.fileImage3 != null) imageMap?.add(
                MultipartBody.Part.createFormData(
                    context.sharedPreferenceUser("id").toString(),
                    HomeFragment.fileImage3?.name,
                    RequestBody.create("image/*".toMediaTypeOrNull(), HomeFragment.fileImage3!!)
                )
            )
            return imageMap!!
        }


        private fun uploadSpinner():MutableMap<String, String>{
            val requestBody: MutableMap<String, String> = mutableMapOf()
            requestBody["id_kategori"] = HomeFragment.idCategory.toString()
            requestBody["id_motif"] = HomeFragment.idMotif.toString()
            requestBody["id_project"] = HomeFragment.idProject.toString()
            requestBody["id_rig"] = HomeFragment.idRig.toString()
            requestBody["id_status_rig"] = HomeFragment.idStatusRig.toString()
            requestBody["id_propinsi"] = HomeFragment.idProvinsi.toString()
            requestBody["id_kab_kota"] = HomeFragment.idKabupaten.toString()
            requestBody["id_kecamatan"] = HomeFragment.idKecamatan.toString()
            requestBody["id_wilayah"] = HomeFragment.idWilayah.toString()
            requestBody["lokasisumur"] = HomeFragment.idSumur.toString()
            requestBody["sumber"] = HomeFragment.idSumber.toString()
            requestBody["peristiwa"] = HomeFragment.idPeristiwa.toString()
            requestBody["tindakan"] = HomeFragment.idTindakan.toString()
            requestBody["kerugian"] = HomeFragment.idKerugian.toString()
            requestBody["catatan"] = HomeFragment.idCatatan.toString()

            return requestBody
        }

    fun postWriteReport(context: Context){
        RestApi.create(context).postWrite(uploadSpinner(), uploadImage(context)).enqueue(object : retrofit2.Callback<WriteReportModel>{
            override fun onFailure(call: Call<WriteReportModel>, t: Throwable) {
                iWriteReport.onDataErrorReport(t)
            }

            override fun onResponse(call: Call<WriteReportModel>, response: Response<WriteReportModel>) {
                iWriteReport.onDataCompleteWriteReport(response.body() as WriteReportModel)
            }
        })
    }
}