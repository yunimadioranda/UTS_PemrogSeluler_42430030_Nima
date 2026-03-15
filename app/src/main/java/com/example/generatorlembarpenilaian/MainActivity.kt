package com.example.generatorlembarpenilaian

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNamaDosen: EditText
    private lateinit var btnMasuk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNamaDosen = findViewById(R.id.etNamaDosen)
        btnMasuk = findViewById(R.id.btnMasuk)

        btnMasuk.setOnClickListener {
            val namaDosen = etNamaDosen.text.toString().trim()

            if (namaDosen.isEmpty()) {
                etNamaDosen.error = "Nama dosen wajib diisi"
                etNamaDosen.requestFocus()
            } else {
                Toast.makeText(this, "Selamat datang, Dosen $namaDosen", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, GeneratorActivity::class.java)
                intent.putExtra("NAMA_DOSEN", namaDosen)
                startActivity(intent)
            }
        }
    }
}