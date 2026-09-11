package com.example.notas_app_sqlite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notas_app_sqlite.databinding.ActivityAgregarnotaBinding
import com.example.notas_app_sqlite.databinding.ActivityMainBinding

class agregarnotaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarnotaBinding
    private lateinit var db : NotasDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgregarnotaBinding.inflate(layoutInflater)
        setContentView(binding.root )
        db = NotasDatabaseHelper(this)

        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

            if (!titulo.isEmpty() && !descripcion.isEmpty()){
                guardarNota(titulo, descripcion)
            }else{
                Toast.makeText(this, "LLENE LOS CAMPOS", Toast.LENGTH_SHORT).show()

            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun guardarNota(titulo : String, descripcion : String){
        val nota = Nota(0, titulo, descripcion)
        db.insertNota(nota)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        Toast.makeText(this, "Se ha agregado la nota", Toast.LENGTH_SHORT).show()

    }


}