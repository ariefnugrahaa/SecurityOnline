package com.example.arief.securityonline.view

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
import android.widget.AdapterView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.extension.spinnerAdapterr
import com.example.arief.securityonline.presenter.SpinnerPresenter
import com.example.arief.securityonline.presenter.WriteReportPresenter
import com.pertamina.pdsi.securityonline.Model.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.RequestBody
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import pub.devrel.easypermissions.EasyPermissions
import java.io.ByteArrayOutputStream
import java.io.File

class HomeFragment : Fragment(),
    BaseInterface.ICategory,
    BaseInterface.IKabupaten,
    BaseInterface.IKecamatan,
    BaseInterface.IMotif,
    BaseInterface.IProject,
    BaseInterface.IProvinsi,
    BaseInterface.IRig,
    BaseInterface.IStatusRig,
    BaseInterface.IWilayah,
    BaseInterface.IWritePresenter{

    private val presenter by lazy {
        SpinnerPresenter(this,
            this,
            this,
            this,
            this,
            this,
            this,
            this,
            this)
    }

    private val presenterWrite by lazy {
        WriteReportPresenter(this)
    }

    companion object {
        var loadCategory: ListCategoryModel? = null
        var loadRig: ListRigModel? = null
        var loadStatusRig: ListStatusRigModel? = null
        var loadProject: ListProjectModel? = null
        var loadMotif: ListMotifModel? = null
        var loadProvinsi: ListProvinsiModel? = null
        var loadKabupaten: ListKabupatenModel? = null
        var loadKecamatan: ListKecamatanModel? = null
        var loadWilayah: ListWilayahModel? = null

        var imageActive : Int ? = null
        var fileImage1: File? = null
        var fileImage2: File? = null
        var fileImage3: File? = null

        var idCategory = 0
        var idMotif = 0
        var idProject = 0
        var idRig = 0
        var idStatusRig = 0
        var idProvinsi = 0
        var idKabupaten = 0
        var idKecamatan = 0
        var idWilayah = 0
        var idSumur:String? = null
        var idSumber:String? = null
        var idPeristiwa:String? = null
        var idTindakan:String? = null
        var idKerugian:String? = null
        var idCatatan:String? = null

    }

    private var GALLERY = 1
    private var CAMERA = 2
    private var imageViewActive: ImageView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        val requestBody = RequestBody.create(MediaType.parse("multipart"), File(pickedImg))

        btn_send_report.onClick {

            idSumur = et_lokasi_sumur.text.toString()
            idSumber = et_sumber.text.toString()
            idPeristiwa = et_peristiwa.text.toString()
            idTindakan = et_tindakan_penyelesaian.text.toString()
            idKerugian = et_kerugian_biaya.text.toString()
            idCatatan = et_catatan.text.toString()

            presenterWrite.postWriteReport(act.applicationContext)

        }

        presenter.getCategory(act.applicationContext)
        presenter.getProject(act.applicationContext)
        presenter.getRig(act.applicationContext)
        presenter.getStatusRig(act.applicationContext)
        presenter.getProvinsi(act.applicationContext)

        setImage()

    }

    override fun onDataCompleteWriteReport(q: WriteReportModel) {
        toast("berhasil")
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
                } catch (e: Exception){}
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

                if (imageActive == 1){
                    fileImage1 = File(filePathStr)
                }
                if (imageActive == 2){
                    fileImage2 = File(filePathStr)
                }
                if (imageActive == 3){
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
        val pictureDialogItems = arrayOf("Pilih dari Galery", "Pilih dari Kamera")
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

        spinner_category.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                    idCategory = p2
                    presenter.getMotif(act.applicationContext, idCategory.toString())
                }
            }
        }
        } catch (e: Exception){}
    }

    //Motif Success
    override fun onDataCompleteMotif(q: ListMotifModel) {

        loadMotif = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Motif-")

        for (i in q.responseData!!.indices){
            spinnerMapKategori[i+1] = q.responseData!![i].iDMotif.toString()
            listSpinner.add(q.responseData!![i].kategoriMotif)
        }

        spinner_motif.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_motif.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                    idMotif = p2
//                    presenter.getMotif(act.applicationContext, idMotif.toString())
                }
            }
        }
    }

    //Project Success
    override fun onDataCompleteProject(q: ListProjectModel) {

        loadProject = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Project-")

        for (i in q.responseData.indices){
            spinnerMapKategori[i+1] = q.responseData[i].IDProject.toString()
            listSpinner.add(q.responseData[i].data)
        }

        spinner_project.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_project.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idProject = p2
            }
        }

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

        spinner_rig.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

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

        spinner_statusrig.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_statusrig.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                idStatusRig = p2
            }
        }
    }

    //Provinsi Sukses
    override fun onDataCompleteProvinsi(q: ListProvinsiModel) {

        loadProvinsi = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Provinsi-")

        for (i in q.responseData!!.indices){
            spinnerMapKategori[i+1] = q.responseData!![i].iDPropinsi.toString()
            listSpinner.add(q.responseData!![i].nama)
        }

        spinner_provinsi.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_provinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                    idProvinsi = p2
                    presenter.getKabupaten(act.applicationContext, idProvinsi.toString())
                }
            }
        }
    }

    //Kabupaten Success
    override fun onDataCompleteKabupaten(q: ListKabupatenModel) {

        loadKabupaten = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Kabupaten-")

        for (i in q.responseData.indices){
            spinnerMapKategori[i+1] = q.responseData[i].iDKabKota.toString()
            listSpinner.add(q.responseData[i].nama)
        }

        spinner_kabupaten.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_kabupaten.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0){
                    idKabupaten = p2
                    presenter.getKecamatan(act.applicationContext, idKabupaten.toString())
                }
            }
        }
    }

    //Kecamatan Success
    override fun onDataCompleteKecamatan(q: ListKecamatanModel) {

        loadKecamatan = q

        val listSpinner = ArrayList<String>()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Kecamatan-")

        for (i in q.responseData.indices){
            spinnerMapKategori[i+1] = q.responseData[i].iDKecamatan.toString()
            listSpinner.add(q.responseData[i].nama)
        }

        spinner_kecamatan.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_kecamatan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0){
                    idKecamatan = p2
                    presenter.getWilayah(act.applicationContext, idKecamatan.toString())
                }
            }
        }
    }

    //Wilayah Success
    override fun onDataCompleteWilayah(q: ListWilayahModel) {

        loadWilayah = q

        val listSpinner:ArrayList<String> = ArrayList()
        val spinnerMapKategori = hashMapOf<Int, String>()

        spinnerMapKategori[0] = "0"
        listSpinner.add("Pilih Kecamatan-")

        for (i in q.responseData.indices){
            spinnerMapKategori[i+1] = q.responseData[i].iDWilayah.toString()
            listSpinner.add(q.responseData[i].nama)
        }

        spinner_wilayah.adapter = act.spinnerAdapterr(act.applicationContext, listSpinner)

        spinner_wilayah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0){
                    idWilayah = p2
                }
            }
        }
    }



    //Category Error
    override fun onDataErrorCategory(t: Throwable) {
    }

    //Kabupaten Error
    override fun onDataErrorKabupaten(t: Throwable) {
    }

    //Kecamatan Error
    override fun onDataErrorKecamatan(t: Throwable) {
    }

    //Motif Error
    override fun onDataErrorMotif(t: Throwable) {
    }

    //Project Error
    override fun onDataErrorProject(t: Throwable) {
    }

    //Provinsi Error
    override fun onDataErrorProvinsi(t: Throwable) {
    }

    //Rig Error
    override fun onDataErrorRig(t: Throwable) {
    }

    override fun onDataErrorStatusRig(t: Throwable) {
    }

    override fun onDataErrorWilayah(t: Throwable) {
    }

    override fun onDataErrorReport(e: Throwable) {
        toast("error")
    }
}