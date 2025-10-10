package pt.ipt.fragmentosv1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var f1:Fragmento
    lateinit var f2:Fragmento
    lateinit var f3:Fragmento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        f1 = Fragmento.newInstance("ola","mundo")
        f2 = Fragmento.newInstance("OLA","mundo")
        f3 = Fragmento.newInstance("OLA","MUNDO")

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmento1, f1)
        fragmentTransaction.add(R.id.fragmento2, f2)
        fragmentTransaction.add(R.id.fragmento3, f3)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
}