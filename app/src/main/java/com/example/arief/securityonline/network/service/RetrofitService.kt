import com.example.arief.securityonline.network.model.*
import com.pertamina.pdsi.securityonline.Model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {

    companion object {

        //Auth
        const val POST_LOGIN = "pdsidas/apisecurity/api/auth/login"
        const val POST_REGISTER = "pdsidas/apisecurity/api/auth/signup"

        //Spinner
        const val GET_CATEGORY  = "pdsidas/apisecurity/api/master/category"
        const val GET_MOTIF     = "pdsidas/apisecurity/api/master/motif"
        const val GET_RIG       = "pdsidas/apisecurity/api/master/rig"
        const val GET_STATUSRIG = "pdsidas/apisecurity/api/master/status-rig"
        const val GET_PROJECT   = "pdsidas/apisecurity/api/master/project"

        //Report
        const val GET_LATEST = "pdsidas/apisecurity/api/reports/latest"
        const val GET_DETAIL = "pdsidas/apisecurity/api/reports/detail"
        const val GET_MYREPORT = "pdsidas/apisecurity/api/reports/your-report"
        const val POST_WRITE = "pdsidas/apisecurity/api/reports/write-report"
        const val GET_LIST_USERS = "pdsidas/apisecurity/api/user/users/"

        //Detail
        const val GET_QUESTREPORT =  "pdsidas/apisecurity/api/reports/your-quest"
        const val POST_SENDQUEST = "pdsidas/apisecurity/api/reports/send-quest"
        const val POST_TAKEQUEST = "pdsidas/apisecurity/api/reports/take-quest"
        const val POST_FOLLOWUP_TBLAPORAN = "pdsidas/apisecurity/api/reports/followup-tblaporan"
        const val POST_FOLLOWUP_TBFOLLOWUP = "pdsidas/apisecurity/api/followup/followup-tbfollowup"
        const val POST_FINISH_QUEST = "pdsidas/apisecurity/api/reports/finish-quest"
        const val POST_APPROVE_QUEST = "pdsidas/apisecurity/api/reports/approve-quest"

        //Profile
//        const val GET_PROFIL = "pdsidas/apisecurity/api/user/"

        //Followup
        const val GET_FOLLOWUP = "pdsidas/apisecurity/api/followup/followup-list"

    }

    //Auth
    @FormUrlEncoded
    @POST(POST_LOGIN)
    fun loginUser(@Field("username") username: String,
                  @Field("password") password: String): Call<LoginModel>

    @POST(POST_REGISTER)
    fun postRegister(@Body params: MutableMap<String, String>): Call<LoginModel>

    //Spinner
    @GET(GET_CATEGORY)
    fun getCategory(): Call<ListCategoryModel>

    @GET(GET_MOTIF)
    fun getMotif(): Call<ListMotifModel>

    @GET(GET_RIG)
    fun getRig(): Call<ListRigModel>

    @GET(GET_STATUSRIG)
    fun getStatusRig(): Call<ListStatusRigModel>

    @GET(GET_PROJECT)
    fun getProject(): Call<ListProjectModel>

    @Multipart
    @POST(POST_WRITE)
    fun postWrite(@Part fileMap: MutableList<@JvmSuppressWildcards MultipartBody.Part> ,
                  @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>): Call<WriteReportModel>

    //Report
    @POST(POST_FOLLOWUP_TBFOLLOWUP)
    fun postFollowup(@Body params: MutableMap<String, String>): Call<PostFollowupModel>

    @POST(POST_FOLLOWUP_TBLAPORAN)
    fun postFollowupQuest(@Body params: MutableMap<String, String>): Call<FinishQuestModel>

    @POST(POST_SENDQUEST)
    fun postSendQuest(@Body params: MutableMap<String, String>): Call<SendQuestModel>

    @POST(POST_TAKEQUEST)
    fun postTakeQuest(@Body params: MutableMap<String, String>): Call<TakeQuestModel>

    @POST(POST_FINISH_QUEST)
    fun postFinishQuest(@Body params: MutableMap<String, String>): Call<FinishQuestModel>

    @POST(POST_APPROVE_QUEST)
    fun postApproveQuest(@Body params: MutableMap<String, String>): Call<ApproveQuestModel>

    @GET(GET_LIST_USERS)
    fun getListRoleUsers(@Query("IdRole")IdRole: String): Call<ListRoleModel>

//    @GET(GET_PROFIL)
//    fun getProfile(): Call<UserDataModel>

    @GET(GET_LATEST)
    fun getLatest(): Call<HomeDataModel>

    @GET(GET_MYREPORT)
    fun getMyreport(@Query("page")page: String): Call<HomeDataModel>

    @GET(GET_QUESTREPORT)
    fun getQuestReport(@Query("page")page: String): Call<HomeDataModel>

    @GET(GET_DETAIL)
    fun getDetailReport(@Query("IDLaporan")iDLaporan: String): Call<HomeDataModel>

    @GET(GET_FOLLOWUP)
    fun getFollowup(@Query("IdLaporan") IdLaporan: String): Call<ListFollowUp>

    @FormUrlEncoded
    @POST("/api/deals")
    fun deals(
        @Header("x-access-token") x_access_token: String?,
        @Field("<parameter_name>") parameter: String?
    ): Call<ResponseBody?>?


}
