package com.example.arief.securityonline.presentation.main.bottomnavigationbar.writereport

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.DATA
import android.provider.MediaStore.Images.Media.getBitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.arief.securityonline.ConstantCamera.pictureDialogItems
import com.example.arief.securityonline.R
import com.example.arief.securityonline.extension.*
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.presenter.WriteReportPresenter
import com.pertamina.pdsi.securityonline.Model.*
import kotlinx.android.synthetic.main.fragment_write_report.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import pub.devrel.easypermissions.EasyPermissions
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.collections.set

@Suppress("DEPRECATION")
class WriteReportFragment : Fragment(),
    BaseInterface.ICategory,
    BaseInterface.IMotif,
    BaseInterface.IProject,
    BaseInterface.IRig,
    BaseInterface.IStatusRig,
    BaseInterface.IWritePresenter{

    private val presenter by lazy { GetSpinnerPresenter(
            this,
            this,
            this,
            this,
            this)
    }

    private val presenterWrite by lazy { WriteReportPresenter(this) }
    private var GALLERY = 1
    private var CAMERA = 2
    private var imageViewActive: ImageView? = null

    companion object {

        var loadCategory: ListCategoryModel? = null
        var loadRig: ListRigModel? = null
        var loadStatusRig: ListStatusRigModel? = null
        var loadProject: ListProjectModel? = null
        var loadMotif: ListMotifModel? = null
        var imageActive : Int ? = null
        var fileImage1: File? = null
        var fileImage2: File? = null
        var fileImage3: File? = null

        var idCategory = 0
        var idMotif = 1
        var idProject = 0
        var idRig = 0
        var idStatusRig = 0
        var idSumur:String? = null
        var idSumber:String? = null
        var idPeristiwa:String? = null
        var idCatatan:String? = null

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_write_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val arraySumber = arrayOf("Pelapor Langsung", "Jabatan Sumber Pelapor")
        val arrayLokasi = arrayOf("Kantor", "Lokasi Sumur", "Yard", "Nama Tempat")
        val arrayPeristiwa = arrayOf("Kronologis / Situasi")

        et_peristiwa.threshold = 0
        et_peristiwa.setAdapter(act.ArrAdaper(arrayPeristiwa))
        et_peristiwa.setOnFocusChangeListener { view, b -> if (b) et_sumber.showDropDown() }

        et_lokasi_sumur.threshold = 0
        et_lokasi_sumur.setAdapter(act.ArrAdaper(arrayLokasi))
        et_lokasi_sumur.setOnFocusChangeListener { view, b -> if (b) et_sumber.showDropDown() }

        et_sumber.threshold = 0
        et_sumber.setAdapter(act.ArrAdaper(arraySumber))
        et_sumber.setOnFocusChangeListener { view, b -> if (b) et_sumber.showDropDown() }

        btn_send_report.onClick {

            btn_send_report.startAnimation()

            if (!validateInput()){

                toast("Data Tidak Lenkgkap")

            } else {

                idSumur = et_lokasi_sumur.text.toString()
                idSumber = et_sumber.text.toString()
                idPeristiwa = et_peristiwa.text.toString()
                idCatatan = et_catatan.text.toString()

                act.window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
                presenterWrite.postWriteReport(act.applicationContext)
            }
        }

        presenter.getCategory(act.applicationContext)
        presenter.getMotif(act.applicationContext)
        presenter.getProject(act.applicationContext)
        presenter.getRig(act.applicationContext)
        presenter.getStatusRig(act.applicationContext)
        setImage()

    }



    private fun validateInput():Boolean{

        val status = true

        if (idProject ==0|| idRig ==0|| idStatusRig ==0){ !status }

        if (!checkETEmpty(et_lokasi_sumur, "Lokasi Sumur")){ !status }
        if (!checkETEmpty(et_sumber, "Sumber")){ !status }
        if (!checkETEmpty(et_peristiwa, "Lokasi Sumur")){ !status }
        if (!checkETEmpty(et_catatan, "Catatan")){ !status }

        if(fileImage1 == null && fileImage2 == null && fileImage3 == null){ !status }

        return status
    }

    private fun autoTextAdapter(arr: Array<String>){


    }

    override fun onDataCompleteWriteReport(q: WriteReportModel) {
        if (q.responseCode == "00") {
            act.showToastSuccessLogin("Berhasil Membuat Laporan")
            startActivity<SuccessReportActivity>()
            act.finish()
        } else {
            act.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            btn_send_report.revertAnimation()
            act.showToastErrorLogin("Gagal membuat laporan")
        }
    }

    private fun setImage(){

        iv_1.setOnClickListener {
            imageViewActive = iv_1
            imageActive = 1
            browseImage()
        }

        iv_2.setOnClickListener {
            imageViewActive = iv_2
            imageActive = 2
            browseImage()
        }

        iv_3.setOnClickListener {
            imageViewActive = iv_3
            imageActive = 3
            browseImage()
        }
    }

    @SuppressLint("Recycle")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val bitmap: Bitmap?

        if (requestCode == GALLERY){
            if (data != null){
                val contentURI = data.data
                val filePath = arrayOf(DATA)
                val c: Cursor = act.contentResolver.query(contentURI!!, filePath, null, null, null)!!
                c.moveToFirst()
                val columnIndex: Int = c.getColumnIndex(filePath[0])
                val filePathStr = c.getString(columnIndex)
                c.close()

                try {
                    bitmap = getBitmap(this.act.contentResolver, contentURI)
                    imageViewActive?.setImageBitmap(bitmap)
                    if (imageActive == 1){
                        fileImage1 = File(filePathStr)
                    }
                    if (imageActive == 2){
                        fileImage2 = File(filePathStr)
                    }
                    if (imageActive == 3){
                        fileImage3 = File(filePathStr)
                    }
                } catch (e: Exception){ }
            }
        } else if (requestCode == CAMERA) {

            if (data != null) {
                bitmap = data.extras?.get("data") as Bitmap
                imageViewActive!!.setImageBitmap(bitmap)

                val contentURI = getImageUri(act.applicationContext, bitmap)
                val filePath = arrayOf(DATA)
                val c: Cursor = act.contentResolver.query(contentURI, filePath, null, null, null)!!
                c.moveToFirst()
                val columnIndex: Int = c.getColumnIndex(filePath[0])
                val filePathStr = c.getString(columnIndex)
                c.close()

                if (imageActive == 1) {
                    fileImage1 = File(filePathStr)
                }
                if (imageActive == 2) {
                    fileImage2 = File(filePathStr)
                }
                if (imageActive == 3) {
                    fileImage3 = File(filePathStr)
                }
            }
        }
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun browseImage(){
        if (EasyPermissions.hasPermissions(act.applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            showPictureDialog()
        } else {
            EasyPermissions.requestPermissions(this, "Aplikasi ini membutuhkan izin anda untuk membuka Galeri", GALLERY, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this.act)
        pictureDialog.setTitle("Select Action")
        pictureDialog.setItems(pictureDialogItems) { dialog, which ->
            when (which) {
                0 -> chooseImageFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun chooseImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA)
    }


    //Category Success
    override fun onDataCompleteCategory(q: ListCategoryModel) {

        try {

        loadCategory = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Category-")

        for (i in q.responseData!!.indices){
            spinnerMapKategori[i+1] = q.responseData!![i].iDKategori.toString()
            listSpinner.add(q.responseData!![i].data)
        }

        spinner_category.adapter = spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {
                        idCategory = p2
                        tv_motif.makeGone()
                        spinner_motif.makeGone()
                    }
                    1 -> {
                        idCategory = p2
                        tv_motif.makeVisibile()
                        spinner_motif.makeVisibile()
//                        presenter.getMotif(act.applicationContext, idCategory.toString())
                    }

                    2 -> {
                        idCategory = p2
                        tv_motif.makeGone()
                        spinner_motif.makeGone()
                    }
                }
            }
        }
        } catch (e: Exception){}
    }

    //Motif Success
    override fun onDataCompleteMotif(q: ListMotifModel) {

        try {

            loadMotif = q

            val listSpinner: ArrayList<String> = ArrayList()
            val spinnerMapKategori = hashMapOf<Int, String>()

            spinnerMapKategori[0] = "0"
            listSpinner.add("Pilih Motif-")

            for (i in q.responseData!!.indices) {
                spinnerMapKategori[i + 1] = q.responseData!![i].iDMotif.toString()
                listSpinner.add(q.responseData!![i].kategoriMotif)
            }

            spinner_motif.adapter = spinnerAdapterr(act.applicationContext, listSpinner)

            spinner_motif.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) = Unit

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> idMotif = 1
                        else -> idMotif = p2
                    }
                }
            }
        } catch (e: Exception){ }
    }

    //Project Success
    override fun onDataCompleteProject(q: ListProjectModel) {

        try {

            loadProject = q

            val listSpinner: ArrayList<String> = ArrayList()
            val spinnerMapKategori = hashMapOf<Int, String>()

            spinnerMapKategori[0] = "0"
            listSpinner.add("Pilih Project-")

            for (i in q.responseData.indices) {
                spinnerMapKategori[i + 1] = q.responseData[i].IDProject.toString()
                listSpinner.add(q.responseData[i].data)
            }

            spinner_project.adapter = spinnerAdapterr(act.applicationContext, listSpinner)

            spinner_project.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    idProject = p2

                }
            }
        } catch (e: Exception) { }
    }

    //Rig Success
    override fun onDataCompleteRig(q: ListRigModel) {

        loadRig = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Rig-")

        for (i in q.responseData!!.indices){
            spinnerMapKategori[i+1] = q.responseData!![i].iDRig.toString()
            listSpinner.add(q.responseData!![i].namaRig)
        }

        spinner_rig.adapter = spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_rig.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idRig = p2
            }
        }
    }

    //StatusRig Success
    override fun onDataCompleteStatusRig(q: ListStatusRigModel) {

        loadStatusRig = q

        val listSpinner = ArrayList<String>()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Status Rig-")

        for (i in q.responseData.indices){
            spinnerMapKategori[i+1] = q.responseData[i].iDStatusRig.toString()
            listSpinner.add(q.responseData[i].status)
        }

        spinner_statusrig.adapter = spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_statusrig.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idStatusRig = p2
            }
        }
    }

    //Category Error
    override fun onDataErrorCategory(t: Throwable) = Unit

    //Motif Error
    override fun onDataErrorMotif(t: Throwable) = Unit

    //Project Error
    override fun onDataErrorProject(t: Throwable) = Unit

    //Rig Error
    override fun onDataErrorRig(t: Throwable) = Unit

    override fun onDataErrorStatusRig(t: Throwable) = Unit

    override fun onDataErrorReport(e: Throwable) {
        btn_send_report.revertAnimation()
        act.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        act.showToastErrorFromServer("Report Error From Server")
    }




}