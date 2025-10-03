package pt.ipt.aplicacaosimples

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnOla: Button = findViewById(R.id.button)
        btnOla.setOnClickListener {
            dizOla()
        }

        var btnMuda: Button = findViewById(R.id.btnMuda)
        btnMuda.setOnClickListener {
            mudaParaOutraAtividade()
        }

    }

    fun dizOla() {
        Log.d("Atenção:", "Vou dizer Olá")
        val txtOla: TextView = findViewById(R.id.txtOla)
        txtOla.text = "Hello World!!"
    }

    fun mudaParaOutraAtividade() {
        val message = "Uma mensagem :-) "
        val intent = Intent(this, OtherActivity::class.java)
        intent.putExtra("EXTRA_MESSAGE", message)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Atenção:", "Vou Salvar")
        val txtOla: TextView = findViewById(R.id.txtOla)
        outState.putString("TXT_VALUE", txtOla.text as String?)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("Atenção:", "Vou recuperar")
        val txtOla: TextView = findViewById(R.id.txtOla)
        txtOla.text = savedInstanceState.getString("TXT_VALUE")
    }

}