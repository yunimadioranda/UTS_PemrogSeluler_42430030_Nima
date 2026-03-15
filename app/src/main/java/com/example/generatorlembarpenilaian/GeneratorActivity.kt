package com.example.generatorlembarpenilaian

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GeneratorActivity : AppCompatActivity() {

    private lateinit var tvSapaan: TextView
    private lateinit var etJumlahMahasiswa: EditText
    private lateinit var etRataRata: EditText
    private lateinit var btnProses: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)

        tvSapaan = findViewById(R.id.tvSapaan)
        etJumlahMahasiswa = findViewById(R.id.etJumlahMahasiswa)
        etRataRata = findViewById(R.id.etRataRata)
        btnProses = findViewById(R.id.btnProses)
        tvHasil = findViewById(R.id.tvHasil)

        val namaDosen = intent.getStringExtra("NAMA_DOSEN") ?: "Tanpa Nama"
        tvSapaan.text = "Selamat bertugas, Dosen $namaDosen"

        btnProses.setOnClickListener {
            prosesData()
        }
    }

    private fun prosesData() {
        val jumlahText = etJumlahMahasiswa.text.toString().trim()
        val rataText = etRataRata.text.toString().trim()

        if (jumlahText.isEmpty()) {
            etJumlahMahasiswa.error = "Jumlah mahasiswa wajib diisi"
            etJumlahMahasiswa.requestFocus()
            return
        }

        if (rataText.isEmpty()) {
            etRataRata.error = "Rata-rata nilai wajib diisi"
            etRataRata.requestFocus()
            return
        }

        val jumlahMahasiswa = jumlahText.toIntOrNull()
        val rataRata = rataText.toDoubleOrNull()

        if (jumlahMahasiswa == null || jumlahMahasiswa <= 0) {
            etJumlahMahasiswa.error = "Masukkan jumlah mahasiswa yang valid"
            etJumlahMahasiswa.requestFocus()
            return
        }

        if (rataRata == null || rataRata < 0 || rataRata > 100) {
            etRataRata.error = "Nilai harus antara 0 sampai 100"
            etRataRata.requestFocus()
            return
        }

        val statusKelas = if (rataRata >= 80) {
            "Sangat Baik"
        } else if (rataRata >= 60) {
            "Cukup"
        } else {
            "Kurang"
        }

        val daftarAbsen = StringBuilder()
        for (i in 1..jumlahMahasiswa) {
            daftarAbsen.append("Mahasiswa $i : ____________________\n")
        }

        val hasil = """
            HASIL EVALUASI KELAS
            
            Status Kelas : $statusKelas
            Jumlah Mahasiswa : $jumlahMahasiswa
            Rata-rata Nilai : $rataRata
            
            Daftar Absen:
            $daftarAbsen
        """.trimIndent()

        tvHasil.text = hasil
        Toast.makeText(this, "Data berhasil diproses", Toast.LENGTH_SHORT).show()
    }
}