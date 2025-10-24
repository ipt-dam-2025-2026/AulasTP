package pt.ipt.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import pt.ipt.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val gson: Gson = GsonBuilder().setStrictness(Strictness.LENIENT).create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ram.ipt.pt/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    fun noteService() = retrofit.create(NoteService::class.java)
}