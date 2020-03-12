package com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.*
import retrofit2.Call
import retrofit2.Response

class GetSpinnerPresenter(iViewCategory: BaseInterface.ICategory,
                          iViewKabupaten: BaseInterface.IKabupaten,
                          iViewKecamatan: BaseInterface.IKecamatan,
                          iViewMotif: BaseInterface.IMotif,
                          iViewProject: BaseInterface.IProject,
                          iViewProvinsi: BaseInterface.IProvinsi,
                          iViewRig: BaseInterface.IRig,
                          iViewStatusRig: BaseInterface.IStatusRig,
                          iViewWilayah: BaseInterface.IWilayah) {



    private val iCategory = iViewCategory
    private val iKabupaten = iViewKabupaten
    private val iKecamatan = iViewKecamatan
    private val iMotif = iViewMotif
    private val iProject = iViewProject
    private val iProvinsi = iViewProvinsi
    private val iRig = iViewRig
    private val iStatusRig = iViewStatusRig
    private val iWilayah = iViewWilayah

    fun getCategory(context: Context) {
        RestApi.create(context).getCategory().enqueue(object : retrofit2.Callback<ListCategoryModel> {
            override fun onFailure(call: Call<ListCategoryModel>, t: Throwable) {
                iCategory.onDataErrorCategory(t)
            }

            override fun onResponse(call: Call<ListCategoryModel>, response: Response<ListCategoryModel>) {
                iCategory.onDataCompleteCategory(response.body() as ListCategoryModel)
            }
        })
    }

    fun getKabupaten(context: Context, kode: String) {
        RestApi.create(context).getKabupaten(kode).enqueue(object : retrofit2.Callback<ListKabupatenModel> {
            override fun onFailure(call: Call<ListKabupatenModel>, t: Throwable) {
                iKabupaten.onDataErrorKabupaten(t)
            }

            override fun onResponse(call: Call<ListKabupatenModel>, response: Response<ListKabupatenModel>) {
                iKabupaten.onDataCompleteKabupaten(response.body() as ListKabupatenModel)
            }
        })
    }

    fun getKecamatan(context: Context, data: String) {
        RestApi.create(context).getKecamatan(data).enqueue(object : retrofit2.Callback<ListKecamatanModel> {
            override fun onFailure(call: Call<ListKecamatanModel>, t: Throwable) {
                iKecamatan.onDataErrorKecamatan(t)
            }

            override fun onResponse(call: Call<ListKecamatanModel>, response: Response<ListKecamatanModel>) {
                iKecamatan.onDataCompleteKecamatan(response.body() as ListKecamatanModel)
            }
        })
    }

    fun getMotif(context: Context, id: String) {
        RestApi.create(context).getMotif(id).enqueue(object : retrofit2.Callback<ListMotifModel> {
            override fun onFailure(call: Call<ListMotifModel>, t: Throwable) {
                iMotif.onDataErrorMotif(t)
            }

            override fun onResponse(call: Call<ListMotifModel>, response: Response<ListMotifModel>) {
                iMotif.onDataCompleteMotif(response.body() as ListMotifModel)
            }
        })
    }

    fun getProject(context: Context) {
        RestApi.create(context).getProject().enqueue(object : retrofit2.Callback<ListProjectModel> {
            override fun onFailure(call: Call<ListProjectModel>, t: Throwable) {
                iProject.onDataErrorProject(t)
            }

            override fun onResponse(call: Call<ListProjectModel>, response: Response<ListProjectModel>) {
                iProject.onDataCompleteProject(response.body() as ListProjectModel)
            }
        })
    }

    fun getProvinsi(context: Context) {
        RestApi.create(context).getProvinsi().enqueue(object : retrofit2.Callback<ListProvinsiModel> {
            override fun onFailure(call: Call<ListProvinsiModel>, t: Throwable) {
                iProvinsi.onDataErrorProvinsi(t)
            }

            override fun onResponse(call: Call<ListProvinsiModel>, response: Response<ListProvinsiModel>) {
                iProvinsi.onDataCompleteProvinsi(response.body() as ListProvinsiModel)
            }
        })
    }

    fun getRig(context: Context) {
        RestApi.create(context).getRig().enqueue(object : retrofit2.Callback<ListRigModel> {
            override fun onFailure(call: Call<ListRigModel>, t: Throwable) {
                iRig.onDataErrorRig(t)
            }

            override fun onResponse(call: Call<ListRigModel>, response: Response<ListRigModel>) {
                iRig.onDataCompleteRig(response.body() as ListRigModel)
            }
        })
    }

    fun getStatusRig(context: Context) {
        RestApi.create(context).getStatusRig().enqueue(object : retrofit2.Callback<ListStatusRigModel> {
            override fun onFailure(call: Call<ListStatusRigModel>, t: Throwable) {
                iStatusRig.onDataErrorStatusRig(t)
            }

            override fun onResponse(call: Call<ListStatusRigModel>, response: Response<ListStatusRigModel>) {
                iStatusRig.onDataCompleteStatusRig(response.body() as ListStatusRigModel)
            }
        })
    }

    fun getWilayah(context: Context, data: String) {
        RestApi.create(context).getWilayah(data).enqueue(object : retrofit2.Callback<ListWilayahModel> {
            override fun onFailure(call: Call<ListWilayahModel>, t: Throwable) {
                iWilayah.onDataErrorWilayah(t)
            }

            override fun onResponse(call: Call<ListWilayahModel>, response: Response<ListWilayahModel>) {
                iWilayah.onDataCompleteWilayah(response.body() as ListWilayahModel)
            }
        })
    }
}