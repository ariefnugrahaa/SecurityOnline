package com.example.arief.securityonline.network.`interface`

import com.example.arief.securityonline.network.model.*
import com.pertamina.pdsi.securityonline.Model.*

class BaseInterface {

    interface IFollow{
        fun onDataCompleteFollow(q: ListFollowUp)
        fun onDataErrorFollow(t: Throwable)
    }

    interface IApproveQuest{
        fun onDataCompleteApproveQuest(q: ApproveQuestModel)
        fun onDataErrorApproveQuest(t: Throwable)
    }

    interface IFinishQuest{
        fun onDataCompleteFinishQuest(q: FinishQuestModel)
        fun onDataErrorFinishQuest(t: Throwable)
    }

    interface ITakeQuest{
        fun onDataCompleteTakeQuest(q: TakeQuestModel)
        fun onDataErrorTakeQuest(t: Throwable)
    }

    interface IFollowup {
        fun onDataCompleteFollowup(q: PostFollowupModel)
        fun onDataErrorFollowup(t: Throwable)
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

    interface IGetDetail{
        fun onDataCompleteGetDetail(q: HomeDataModel)
        fun onErrorGetDetail(t:Throwable)
    }

    interface IGetListRole{
        fun onDataCompleteGetListRole(q: ListRoleModel)
        fun onErrorGetListRole(t: Throwable)
    }

    interface IGetQuestReport{
        fun onDataCompleteGetQuestReport(q: HomeDataModel)
        fun onErrorGetQuestReport(t: Throwable)
//        fun onLoading(b: Boolean)
    }

    interface IPostSendQuest{
        fun onDataCompletePostQuestReport(q: SendQuestModel)
        fun onErrorPostRequestReport(t: Throwable)
    }

    interface IPostFollowupQuest{
        fun onDataCompleteFollowupQuest(q: FinishQuestModel)
        fun onDataErrorFollowupQuest(t: Throwable)
    }

}