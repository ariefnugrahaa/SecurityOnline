import com.pertamina.pdsi.securityonline.Model.LoginModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    companion object {
        const val POST_LOGIN = "api/auth/login"

        // home
        const val LATEST = "/api/reports/latest"

        // detail
        const val DETAIL = "/api/reports/detail"
//        /api/reports/detail?IDLaporan=1

        // masters
        const val CATEGORY  = "/api/master/category"
        const val MOTIF     = "/api/master/motif"
        //        const val MOTIF     = "/api/master/motif?IDKategori=1"
        const val RIG       = "/api/master/rig"
        const val STATUSRIG = "/api/master/status-rig"
        const val PROJECT   = "/api/master/project"
        const val PROVINSI  = "/api/master/province"
        const val KABUPATEN = "/api/master/districts"
        //        const val KABUPATEN = "/api/master/districts?kode=35"
        const val KECAMATAN = "/api/master/sub-districts"
        //        const val KECAMATAN = "/api/master/sub-districts?kode=3507"
        const val WILAYAH   = "/api/master/area"
//        const val WILAYAH   = "/api/master/area?kode=350729"

        // profil
        const val PROFIL = "/api/user/"

        // task
        const val MYREPORT = "/api/reports/your-report"
//        /api/reports/your-report?page=1

        // write
        const val WRITE = "/api/reports/write-report"

    }

    @FormUrlEncoded
    @POST(POST_LOGIN)
    fun loginUser(@Field("username") username: String,
                  @Field("password") password: String): Call<LoginModel>


}
