package pt.ipt.apiv2.retrofit.service

import pt.ipt.apiv2.model.APIResult
import pt.ipt.apiv2.model.Note
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteService {
    @GET("API/getNotes.php")
    fun list(): Call<List<Note>>

    @GET("API/reset.php")
    fun resetList(): Call<List<Note>>

    @FormUrlEncoded
    @POST("API/addNote.php")
    fun addNote(@Field("title") title: String?, @Field("description") description: String?): Call<APIResult>

}