package pt.ipt.api.retrofit

import pt.ipt.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ram.ipt.pt/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun noteService() = retrofit.create(NoteService::class.java)
}