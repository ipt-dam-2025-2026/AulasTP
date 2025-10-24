package pt.ipt.api.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pt.ipt.api.R
import pt.ipt.api.model.APIResult
import pt.ipt.api.model.Note
import pt.ipt.api.retrofit.RetrofitInitializer
import pt.ipt.api.ui.activity.adapter.NoteListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listNotes()
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            addDummyNote()
        }

        val buttonReset: Button = findViewById(R.id.buttonReset)
        buttonReset.setOnClickListener {
            resetNotes()
        }

    }

    private fun listNotes() {
        val call = RetrofitInitializer().noteService().list()
        refreshList(call)
    }

    private fun resetNotes() {
        val call = RetrofitInitializer().noteService().resetList()
        refreshList(call)
    }

    private fun refreshList(call: Call<List<Note>>){
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(call: Call<List<Note>?>?,
                                    response: Response<List<Note>?>?) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                t?.message?.let { Log.e("onFailure error", it) }
            }
        })
    }


    private fun configureList(notes: List<Note>) {
        val recyclerView: RecyclerView = findViewById(R.id.note_list_recyclerview)
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    fun addDummyNote() {
        val i = Random.nextInt(100)
        val note = Note("Note "+ i, "Descrition " + i )

        addNote(note) {
            Toast.makeText(this,"Add " + it?.description,Toast.LENGTH_SHORT).show()
        }

        listNotes()
    }

    private fun addNote(note: Note, onResult: (APIResult?) -> Unit){
        val call = RetrofitInitializer().noteService().addNote(note.title, note.description)
        call.enqueue(
            object : Callback<APIResult> {
                override fun onFailure(call: Call<APIResult>, t: Throwable) {
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse( call: Call<APIResult>, response: Response<APIResult>) {
                    val addedNote = response.body()
                    onResult(addedNote)
                }
            }
        )


    }
}