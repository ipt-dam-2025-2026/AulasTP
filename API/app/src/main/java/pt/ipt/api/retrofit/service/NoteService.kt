package pt.ipt.api.retrofit.service

import pt.ipt.api.model.Note
import retrofit2.Call
import retrofit2.http.GET

interface NoteService {
    @GET("API/getNotes.php")
    fun list(): Call<List<Note>>
}