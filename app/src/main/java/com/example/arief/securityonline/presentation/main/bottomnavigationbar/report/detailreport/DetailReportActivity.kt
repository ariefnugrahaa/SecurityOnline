package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import com.example.arief.securityonline.ConstantRole.ASISTEN_MANAGER
import com.example.arief.securityonline.ConstantRole.DIAJUKAN
import com.example.arief.securityonline.ConstantRole.DIKERJAKAN
import com.example.arief.securityonline.ConstantRole.DILAPORKAN
import com.example.arief.securityonline.ConstantRole.FOLLOWUP
import com.example.arief.securityonline.ConstantRole.MANAGER
import com.example.arief.securityonline.ConstantRole.SELESAI
import com.example.arief.securityonline.R
import com.example.arief.securityonline.extension.*
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.network.model.ApproveQuestModel
import com.example.arief.securityonline.network.model.FinishQuestModel
import com.example.arief.securityonline.network.model.PostFollowupModel
import com.example.arief.securityonline.network.model.TakeQuestModel
import com.example.arief.securityonline.network.presenter.PostTableFollowupPresenter
import com.example.arief.securityonline.presentation.main.MainActivity
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.followup.FollowupActivity
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.listuser.ListRoleActivity
import com.example.arief.umkpdconline.common.showSnackbarSuccess
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.animations.DescriptionAnimation
import com.glide.slider.library.slidertypes.BaseSliderView
import com.glide.slider.library.slidertypes.TextSliderView
import com.glide.slider.library.tricks.ViewPagerEx
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import com.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_addnote.view.*
import org.jetbrains.anko.startActivity

class DetailReportActivity : AppCompatActivity(),
                            BaseInterface.IGetDetail,
                            BaseInterface.IFollowup,
                            BaseInterface.ITakeQuest,
                            BaseInterface.IFinishQuest,
                            BaseInterface.IApproveQuest,
                            BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    //Throw Variable
    companion object {
        const val INTENT_EXTRA = "a.b"
        const val INTENT_STATUS_REPORT = "intent.statusreport"
        const val INTENT_SENDTINDAKAN_REPORT = "intent.sendtindakan"
        var tindakanPenyelesaian:String? = null
        var kerugian:String? = null
        var idLaporan:Int? = null
        var username:String? = null
    }

    //Init Presenter
    private val finishReportPresenter by lazy { PostFinishQuestPresenter(this) }
    private val tablefollowupPresenter by lazy { PostTableFollowupPresenter (this) }
    private val takeQuestPresenter by lazy { PostTakeQuestPresenter(this) }
    private val presenter by lazy { GetDetailPresenter(this) }
    private val approvePresenter by lazy { PostApprovePresenter(this)}

    // Global Variable
    private val sharedPreference by lazy { SharedPrefManager.getInstance(this) }
    private var loadData = mutableListOf<HomeDataModel.ResponseData>()
    lateinit var mAlertDialog: AlertDialog

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()

    }

    override fun onStop() {
        iv_detail.stopAutoCycle()
        super.onStop()
    }

    @SuppressLint("RestrictedApi")
    private fun initView(){

        val getRole = sharedPreference.getValueRole("Role").toString()
        val getStatusReport = intent.getIntExtra(INTENT_STATUS_REPORT, 0)
        val getExtra = intent.getIntExtra(INTENT_EXTRA, 0)
        val getTindakan = intent.getStringExtra(INTENT_SENDTINDAKAN_REPORT)

        username = sharedPreference.getValueUsername("Username")
        idLaporan = getExtra

        presenter.getDetailReport(this, getExtra.toString())

        //Manager
        if (getRole == MANAGER && getStatusReport == DILAPORKAN){
            btn_detail_finish.makeVisibile()
            btn_assignto.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        if (getRole == MANAGER && getStatusReport == DIAJUKAN){
            btn_detail_finish.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        if (getRole == MANAGER && getStatusReport == SELESAI){
            btn_detail_followup.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        if (getRole == MANAGER && getStatusReport == FOLLOWUP){
            btn_detail_followup.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        //ASISTEN MANAGER
        if (getRole == ASISTEN_MANAGER && getStatusReport == DILAPORKAN){
            btn_detail_finish.makeVisibile()
            btn_assignto.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        if (getRole == ASISTEN_MANAGER && getStatusReport == DIAJUKAN){
            btn_detail_finish.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        if (getRole == ASISTEN_MANAGER && getStatusReport == SELESAI){
            btn_detail_followup.makeVisibile()
            btn_detail_approve.makeVisibile()
        }

        if (getRole == ASISTEN_MANAGER && getStatusReport == FOLLOWUP){
            btn_detail_followup.makeVisibile()
            btn_detail_approve.makeVisibile()
        }


        if (username == getTindakan && getStatusReport == DIAJUKAN){
            btn_detail_takequest.makeVisibile()
        }

        if (username == getTindakan && getStatusReport == DIAJUKAN && getRole == ASISTEN_MANAGER){
            btn_assignto.makeVisibile()
            btn_detail_takequest.makeVisibile()
        }

        if (username == getTindakan && getStatusReport == DIKERJAKAN){
            btn_detail_takequest.makeGone()
            btn_detail_finish.makeVisibile()
        }

        if (username == getTindakan && getStatusReport == FOLLOWUP){
            btn_detail_finish.makeVisibile()
        }


        btn_assignto.setOnClickListener {
            val intent = Intent(this, ListRoleActivity::class.java).apply {
                putExtra(ListRoleActivity.INTENT_IDLAPORAN, getExtra)
            }
            startActivity(intent)
        }

        btn_detail_followup.setOnClickListener {

            val intent = Intent(this, ListRoleActivity::class.java).apply {
                putExtra(ListRoleActivity.INTENT_IDLAPORAN, getExtra)
                putExtra(ListRoleActivity.INTENT_STATUS, getStatusReport)
            }
            startActivity(intent)
        }

        btn_detail_takequest.setOnClickListener {

            MaterialDialog.Builder(this)
                .setTitle("Ambil Tugas")
                .setMessage("Anda ingin mengambil tugas ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", R.drawable.ic_check) { dialogInterface, _ ->
                    takeQuestPresenter.postTakeQuest(this)
                    dialogInterface.dismiss()
                    startActivity<MainActivity>()
                    finish()
                }

                .setNegativeButton("Tidak", R.drawable.ic_close) { dialogInterface, _ ->
                    showSnackbarSuccess("Selamat Beraktifitas Kembali")
                    dialogInterface.dismiss()
                }
                .setAnimation("choosesecurity.json")
                .build()
                .show()
        }

        btn_detail_finish.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.item_addnote, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            mAlertDialog = mBuilder.show()

            mDialogView.btn_addnote.setOnClickListener {

                val followupTindakan = mDialogView.et_followup_tindakanpenyelesaian.text.toString()
                val followupKerugian = mDialogView.et_followup_kerugian.text.toString()

                if (followupTindakan.isEmpty() || followupKerugian.isEmpty()){
                    showToastErrorRegister("Tolong Lengkapi Data")
                } else {
                    tindakanPenyelesaian = followupTindakan
                    kerugian = followupKerugian

                    finishReportPresenter.finishQuest(this)
                    tablefollowupPresenter.postTableFollowup(this)
                }

            }
        }

        btn_detail_list_followup.setOnClickListener {
            val intent = Intent(this, FollowupActivity::class.java).apply {
                putExtra(FollowupActivity.INTENT_ID_FOLLOWUP, getExtra)
            }
            startActivity(intent)

        }

        btn_detail_approve.setOnClickListener {
            approvePresenter.postApprove(this)
        }
    }

    override fun onDataCompleteGetDetail(q: HomeDataModel) {

        loadData = q.responseData.toMutableList()

            try {
                tv_detail_head_peristiwa.text = q.responseData[0].peristiwa
                tv_detail_approveby.text = q.responseData[0].ApproveBy
                tv_detail_takeby.text = q.responseData[0].TakeBy
                tv_detail_tanggalmasuk.text = q.responseData[0].tanggalMasuk
                tv_detail_userid.text = q.responseData[0].userID
                tv_detail_peristiwa.text = q.responseData[0].peristiwa
                tv_detail_lokasisumur.text = q.responseData[0].lokasiSumur
                tv_detail_catatan.text = q.responseData[0].catatan
                tv_detail_kategori.text = q.responseData[0].kategori
                tv_detail_rig.text = q.responseData[0].rig
                tv_detail_motif.text = q.responseData[0].motif
                tv_detail_project.text = q.responseData[0].project
                tv_detail_statuslaporan.text = q.responseData[0].statusLaporan

                val listUrl = loadData[0].foto!!.toMutableList()

                for (i in listUrl.indices) {
                    val sliderView = TextSliderView(this)

                    sliderView
                        .image(listUrl[i])
                        .setRequestOption(RequestOptions().centerCrop())
                        .setOnSliderClickListener(this)
                        .setProgressBarVisible(true)

                    sliderView.bundle(Bundle())
                    iv_detail.addSlider(sliderView)
                }

                iv_detail.setPresetTransformer(SliderLayout.Transformer.Accordion)
                iv_detail.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
                iv_detail.setCustomAnimation(DescriptionAnimation())
                iv_detail.setDuration(4000)
                iv_detail.addOnPageChangeListener(this)
                iv_detail.stopCyclingWhenTouch(false)

            } catch (e: Exception) { }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDataCompleteFollowup(q: PostFollowupModel) {

        if (q.responseCode == "00"){
            showToastSuccessLogin("Success Follow Up")
            mAlertDialog.dismiss()
            startActivity<MainActivity>()
            finish()
        } else {
            showToastErrorFromServer("Fail to Followup")
        }
    }

    override fun onDataCompleteTakeQuest(q: TakeQuestModel) {

        if (q.responseCode == "00"){
            showToastSuccessLogin("Success Take Quest")
            startActivity<MainActivity>()
            finish()
        } else {
            showToastErrorFromServer("Fail Take Quest")
        }
    }

    override fun onDataCompleteFinishQuest(q: FinishQuestModel) {
        if (q.responseCode == "00"){
            showToastSuccessLogin("Success Finish Quest")
            mAlertDialog.dismiss()
            startActivity<MainActivity>()
            finish()
        } else {
            showToastErrorFromServer("Succes Finish Quest")
        }
    }

    override fun onDataCompleteApproveQuest(q: ApproveQuestModel) {

        if (q.responseCode == "00"){
            showToastSuccessLogin("Success Approve")
            startActivity<MainActivity>()
            finish()
        } else {
            showToastErrorFromServer("Fail Approve")
        }
    }


    override fun onErrorGetDetail(t: Throwable) = Unit

    override fun onDataErrorFollowup(t: Throwable) = Unit

    override fun onSliderClick(slider: BaseSliderView?) = Unit

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

    override fun onPageSelected(position: Int) = Unit

    override fun onDataErrorTakeQuest(t: Throwable) = Unit

    override fun onDataErrorFinishQuest(t: Throwable) = Unit

    override fun onDataErrorApproveQuest(t: Throwable) = Unit

}
