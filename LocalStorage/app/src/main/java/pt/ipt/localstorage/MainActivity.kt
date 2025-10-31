package pt.ipt.localstorage

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintStream
import java.util.Scanner

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

        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            requestPermissions(permission, 112)
        }

    }

    fun escreverSharedPreferences(view: View) {
        // escrita usando o sharedPreferences
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("NOME", "Paulo")
        editor.putInt("IDADE", 47)
        editor.commit()
        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
    }

    fun lerSharedPreferences(view: View) {
        // leitura usando o sharedPreferences
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val nome = sharedPreferences.getString("NOME","Sem Nome")
        val idade = sharedPreferences.getInt("IDADE", 0)
        Toast.makeText(this,"Olá " + nome + " de " + idade + " anos.",Toast.LENGTH_LONG).show()

    }

    fun escreverSharedPreferencesVariosFicheiros(view: View) {
        // escrita usando o sharedPreferences
        val sharedPreferencesNomes = getSharedPreferences("nomes.dat", MODE_PRIVATE)
        val sharedPreferencesIdades = getSharedPreferences("idades.dat", MODE_PRIVATE)
        val editorNomes: SharedPreferences.Editor = sharedPreferencesNomes.edit()
        val editorIdades: SharedPreferences.Editor = sharedPreferencesIdades.edit()
        editorNomes.putString("NOME", "Paulo")
        editorIdades.putInt("IDADE", 47)
        editorNomes.commit()
        editorIdades.commit()
        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
    }

    fun lerSharedPreferencesVariosFicheiros(view: View) {
        // leitura usando o sharedPreferences
        val sharedPreferencesNomes = getSharedPreferences("nomes.dat", MODE_PRIVATE)
        val sharedPreferencesIdades = getSharedPreferences("idades.dat", MODE_PRIVATE)
        val nome = sharedPreferencesNomes.getString("NOME","Sem Nome")
        val idade = sharedPreferencesIdades.getInt("IDADE", 0)
        Toast.makeText(this,"Olá " + nome + " de " + idade + " anos.",Toast.LENGTH_LONG).show()
    }

    fun escreverInternalStorage(view: View) {
        // escrita no internal Storage
        val directory: File = getFilesDir()
        val file: File  = File(directory, "dados.txt")
        val fo: FileOutputStream = FileOutputStream(file)
        val ps: PrintStream = PrintStream(fo)
        ps.println("Paulo")
        ps.println("47")
        ps.close()
        fo.close()
        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
    }

    fun lerInternalStorage(view: View) {
        // leitura do internal Storage
        val directory: File  = getFilesDir()
        val file: File  = File(directory, "dados.txt")
        try {
            val fi: FileInputStream = FileInputStream(file)
            val sc: Scanner = Scanner(fi)
            val nome = sc.nextLine()
            val idade = sc.nextLine()
            sc.close()
            fi.close()
            Toast.makeText(this, "Olá " + nome + " de " + idade + " anos.", Toast.LENGTH_LONG).show()
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show()
        }
    }

    fun escreverExternalStorage(view: View) {
        // escrita no External Storage
        val directory: File = getExternalFilesDir(Environment.DIRECTORY_DCIM)!!
        val file: File  = File(directory, "dados.txt")
        val fo: FileOutputStream = FileOutputStream(file)
        val ps: PrintStream = PrintStream(fo)
        ps.println("Paulo")
        ps.println("47")
        ps.close()
        fo.close()
        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
    }

    fun lerExternalStorage(view: View) {
        // leitura do external Storage
        val directory: File  = getExternalFilesDir(Environment.DIRECTORY_DCIM)!!
        val file: File  = File(directory, "dados.txt")
        try {
            val fi: FileInputStream = FileInputStream(file)
            val sc: Scanner = Scanner(fi)
            val nome = sc.nextLine()
            val idade = sc.nextLine()
            sc.close()
            fi.close()
            Toast.makeText(this, "Olá " + nome + " de " + idade + " anos.", Toast.LENGTH_LONG).show()
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show()
        }
    }

    fun escreverCache(view: View) {
        // escrita na cache
        val directory: File = getCacheDir()
        val file: File  = File(directory, "dados.txt")
        val fo: FileOutputStream = FileOutputStream(file)
        val ps: PrintStream = PrintStream(fo)
        ps.println("Paulo")
        ps.println("47")
        ps.close()
        fo.close()
        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
    }

    fun lerCache(view: View) {
        // leitura da cache
        val directory: File  = getCacheDir()
        val file: File  = File(directory, "dados.txt")
        try {
            val fi: FileInputStream = FileInputStream(file)
            val sc: Scanner = Scanner(fi)
            val nome = sc.nextLine()
            val idade = sc.nextLine()
            sc.close()
            fi.close()
            Toast.makeText(this, "Olá " + nome + " de " + idade + " anos.", Toast.LENGTH_LONG).show()
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show()
        }
    }
}