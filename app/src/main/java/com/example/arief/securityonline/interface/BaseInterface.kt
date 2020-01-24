package com.example.arief.securityonline.`interface`

import com.pertamina.pdsi.securityonline.Model.*

class BaseInterface {

    interface ILogin {
        fun onDataCompleteLogin(q: LoginModel)
        fun onDataErrorLogin(e: Throwable)
    }

    interface IWritePresenter {
        fun onDataCompleteWriteReport(q: WriteReportModel)
        fun onDataErrorReport(e: Throwable)
    }

    interface ICategory {
        fun onDataCompleteCategory(q: ListCategoryModel)
        fun onDataErrorCategory(t: Throwable)
    }

    interface IKabupaten {
        fun onDataCompleteKabupaten(q: ListKabupatenModel)
        fun onDataErrorKabupaten(t: Throwable)
    }

    interface IKecamatan {
        fun onDataCompleteKecamatan(q: ListKecamatanModel)
        fun onDataErrorKecamatan(t: Throwable)
    }


    interface IMotif {
        fun onDataCompleteMotif(q: ListMotifModel)
        fun onDataErrorMotif(t: Throwable)
    }


    interface IProject {
        fun onDataCompleteProject(q: ListProjectModel)
        fun onDataErrorProject(t: Throwable)
    }

    interface IProvinsi {
        fun onDataCompleteProvinsi(q: ListProvinsiModel)
        fun onDataErrorProvinsi(t: Throwable)
    }

    interface IRig {
        fun onDataCompleteRig(q: ListRigModel)
        fun onDataErrorRig(t: Throwable)
    }

    interface IStatusRig {
        fun onDataCompleteStatusRig(q: ListStatusRigModel)
        fun onDataErrorStatusRig(t: Throwable)
    }

    interface IWilayah {
        fun onDataCompleteWilayah(q: ListWilayahModel)
        fun onDataErrorWilayah(t: Throwable)
    }

    interface IUser{
        fun onDataCompleteUser(q: UserDataModel)
        fun onErrorCompleteUser(t: Throwable)
    }

    interface IListReportLatest{
        fun onDataCompleteListReport(q: HomeDataModel)
        fun onErrorListReport(t: Throwable)
    }

    interface IMyReport{
        fun onDataCompleteMyReport(q: HomeDataModel)
        fun onErrorMyReport(t: Throwable)
    }
}