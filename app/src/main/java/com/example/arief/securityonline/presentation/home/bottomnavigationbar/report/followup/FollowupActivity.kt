package com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.followup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arief.securityonline.R
import com.example.arief.securityonline.adapter.RVListFollowUp
import com.example.arief.securityonline.extension.makeGone
import com.example.arief.securityonline.extension.makeVisibile
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.ListFollowUp
import com.example.arief.securityonline.network.presenter.GetListFollowupPresenter
import kotlinx.android.synthetic.main.activity_followup.*

class FollowupActivity : AppCompatActivity(), BaseInterface.IFollow {

    companion object {
        const val INTENT_ID_FOLLOWUP = "INTENT.ID.FOLLOWUP"
    }

    private var loadData = mutableListOf<ListFollowUp.ResponseData>()
    private val presenter by lazy { GetListFollowupPresenter(this) }
    lateinit var rvAdapter:RVListFollowUp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followup)

        val getIdLaporan = intent.getIntExtra(INTENT_ID_FOLLOWUP, 0)

        rvAdapter = RVListFollowUp { position -> null }

        presenter.getListFollowup(this, getIdLaporan.toString())

    }

    override fun onDataCompleteFollow(q: ListFollowUp) {

        try {

            loadData = q.responseData.toMutableList()

            if (loadData.isNullOrEmpty()){
                lottie_followup.setAnimation("notfound.json")
                lottie_followup.playAnimation()
                lottie_followup.makeVisibile()
            } else {
                rv_followup.apply {
                    rvAdapter.setData(loadData)
                    layoutManager = LinearLayoutManager(this@FollowupActivity)
                    adapter = rvAdapter
                }
                lottie_followup.makeGone()
            }
        } catch (e: Exception) { }

        }

    override fun onDataErrorFollow(t: Throwable) =  Unit

}




