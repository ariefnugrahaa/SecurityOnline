package com.example.arief.securityonline.presentation.main.bottomnavigationbar.writereport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.*
import retrofit2.Call
import retrofit2.Response

class GetSpinnerPresenter(iViewCategory: BaseInterface.ICategory,
                          iViewMotif: BaseInterface.IMotif,
                          iViewProject: BaseInterface.IProject,
                          iViewRig: BaseInterface.IRig,
                          iViewStatusRig: BaseInterface.IStatusRig
                          ) {



    private val iCategory = iViewCategory
    private val iMotif = iViewMotif
    private val iProject = iViewProject
    private val iRig = iViewRig
    private val iStatusRig = iViewStatusRig

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

    fun getMotif(context: Context) {
        RestApi.create(context).getMotif().enqueue(object : retrofit2.Callback<ListMotifModel> {
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
}