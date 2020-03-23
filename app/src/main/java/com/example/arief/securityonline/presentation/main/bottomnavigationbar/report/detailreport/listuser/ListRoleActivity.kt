package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.listuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arief.securityonline.R
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.adapter.RVListUsers
import com.example.arief.securityonline.network.model.FinishQuestModel
import com.example.arief.securityonline.network.model.ListRoleModel
import com.example.arief.securityonline.network.model.SendQuestModel
import com.example.arief.securityonline.network.presenter.PostFollowupQuestPresenter
import com.example.arief.securityonline.presentation.main.MainActivity
import com.example.arief.umkpdconline.common.showSnackbarSuccess
import com.example.arief.securityonline.extension.showToastErrorRegister
import com.example.arief.securityonline.extension.showToastSuccessRegister
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_list_role.*
import org.jetbrains.anko.startActivity

class ListRoleActivity : AppCompatActivity(),
                        BaseInterface.IGetListRole,
                        BaseInterface.IPostSendQuest,
                        BaseInterface.IPostFollowupQuest
{

    //Init Presenter
    private val presenter by lazy { GetListRolePresenter(this, this) }
    private val followupReportPresenter by lazy { PostFollowupQuestPresenter(this) }

    //Local Variable
    private lateinit var rvListRole:RVListUsers
    private val sharedPreference by lazy { SharedPrefManager.getInstance(this) }

    companion object {
        const val INTENT_IDLAPORAN = "INTENT.ID"
        const val INTENT_STATUS = "INTENT.STATUS"
        var loadData = mutableListOf<ListRoleModel.ResponseData>()
        var getExtra:Int? = null
        var getUser:String? = null
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_role)

        val getStatus = intent.getIntExtra(INTENT_STATUS, 0)
        val getRole = sharedPreference.getValueRole("Role").toString()

        when (getRole){
            "M" -> presenter.getListReport(this, "ALL")
            "AM" -> presenter.getListReport(this, "S")
        }

        rvListRole = RVListUsers { position ->

            getExtra = intent.getIntExtra(
                INTENT_IDLAPORAN, 0)

            MaterialDialog.Builder(this)
                .setTitle("Pilih Security")
                .setMessage("Anda ingin menunjuk ${loadData[position].userID} sebagai security ?")
                .setCancelable(false)
                .setPositiveButton("Ya", R.drawable.ic_check) { dialogInterface, _ ->

                    getUser = loadData[position].userID

                    if (getStatus == 4) {
                        presenter.postSendQuest(this)
                        followupReportPresenter.followupQuest(this)
                        dialogInterface.dismiss()
                        startActivity<MainActivity>()
                        finish()

                    } else {

                        presenter.postSendQuest(this)
                        dialogInterface.dismiss()
                        startActivity<MainActivity>()
                        finish()
                    }
                }

                .setNegativeButton("Tidak", R.drawable.ic_close) { dialogInterface, _ ->
                    showSnackbarSuccess("Selamat Beraktifitas Kembali")
                    dialogInterface.dismiss()
                }

                .setAnimation("choosesecurity.json")
                .build()
                .show()
        }
    }


    override fun onDataCompleteFollowupQuest(q: FinishQuestModel) {
        if (q.responseCode == "00"){
            showToastSuccessRegister("Berhasil Menunjuk Followup")
            finish()
        } else {
            showToastErrorRegister("Gagal Menunjuk Followup")
        }
    }

    override fun onDataCompleteGetListRole(q: ListRoleModel) {

        loadData = q.responseData!!.toMutableList()

        rv_listrole.apply {
            rvListRole.setData(loadData)
            layoutManager = LinearLayoutManager(this@ListRoleActivity)
            adapter = rvListRole
        }
    }

    override fun onDataCompletePostQuestReport(q: SendQuestModel) {
        if (q.responseCode == "00"){
            showToastSuccessRegister("Berhasil Menunjuk Security")
            finish()
        } else {
            showToastErrorRegister("Gagal Menunjuk Security")
        }
    }

    override fun onErrorGetListRole(t: Throwable) = Unit

    override fun onErrorPostRequestReport(t: Throwable) = Unit

    override fun onBackPressed() {
        finish()
    }

    override fun onDataErrorFollowupQuest(t: Throwable)  = Unit

}
