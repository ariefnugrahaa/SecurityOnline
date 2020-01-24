import com.pertamina.pdsi.securityonline.Model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    companion object {
        const val POST_LOGIN = "/api/auth/login"

        // home
        const val GET_LATEST = "/api/reports/latest"

        // detail
        const val DETAIL = "/api/reports/detail"
//        /api/reports/detail?IDLaporan=1

        // masters
        const val GET_CATEGORY  = "api/master/category"
        const val GET_MOTIF     = "api/master/motif"
        //        const val MOTIF     = "/api/master/motif?IDKategori=1"
        const val GET_RIG       = "/api/master/rig"
        const val GET_STATUSRIG = "/api/master/status-rig"
        const val GET_PROJECT   = "/api/master/project"
        const val GET_PROVINSI  = "/api/master/province"
        const val GET_KABUPATEN = "/api/master/districts"
        //        const val KABUPATEN = "/api/master/districts?kode=35"
        const val GET_KECAMATAN = "/api/master/sub-districts"
        //        const val KECAMATAN = "/api/master/sub-districts?kode=3507"
        const val GET_WILAYAH   = "/api/master/area"
//        const val WILAYAH   = "/api/master/area?kode=350729"

        // profil
        const val GET_PROFIL = "/api/user/"

        // task
        const val GET_MYREPORT = "/api/reports/your-report"
//        /api/reports/your-report?page=1

        // write
        const val POST_WRITE = "/api/reports/write-report"

    }

    @FormUrlEncoded
    @POST(POST_LOGIN)
    fun loginUser(@Field("username") username: String,
                  @Field("password") password: String): Call<LoginModel>



    @GET(GET_LATEST)
    fun getLatest(): Call<HomeDataModel>

    @GET(GET_MYREPORT)
    fun getMyreport(@Query("page")page: String): Call<HomeDataModel>




    @GET(GET_PROFIL)
    fun getProfile(): Call<UserDataModel>

    @GET(GET_CATEGORY)
    fun getCategory(): Call<ListCategoryModel>

    @GET(GET_MOTIF)
    fun getMotif(@Query("IDMotif")IDMotif: String): Call<ListMotifModel>

    @GET(GET_RIG)
    fun getRig(): Call<ListRigModel>

    @GET(GET_STATUSRIG)
    fun getStatusRig(): Call<ListStatusRigModel>

    @GET(GET_PROJECT)
    fun getProject(): Call<ListProjectModel>

    @GET(GET_PROVINSI)
    fun getProvinsi(): Call<ListProvinsiModel>

    @GET(GET_KABUPATEN)
    fun getKabupaten(@Query("kode")kode: String): Call<ListKabupatenModel>

    @GET(GET_KECAMATAN)
    fun getKecamatan(@Query("kode")kode: String): Call<ListKecamatanModel>

    @GET(GET_WILAYAH)
    fun getWilayah(@Query("kode")kode: String): Call<ListWilayahModel>

    @Multipart
    @POST(POST_WRITE)
    fun postWrite(@Body body: MutableMap<String, String>,
                  @Part fileMap: MutableList<MultipartBody.Part>): Call<WriteReportModel>

//    @Part fileMap: MutableList<@JvmSuppressWildcards MultipartBody.Part>



}
