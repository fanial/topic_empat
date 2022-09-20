package com.fal.topic_empat

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fal.topic_empat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("STUDENT", Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            saveData()
            Toast.makeText(this, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show()
        }
        binding.btnView.setOnClickListener {
            viewData()
            Toast.makeText(this, "Data ditampilkan", Toast.LENGTH_SHORT).show()
        }

    }

    fun saveData(){
        var getNim = binding.etID.text.toString()
        var getNama = binding.etNama.text.toString()

        var addData = sharedPreferences.edit()
        addData.putString("nim", getNim)
        addData.putString("nama", getNama)
        addData.apply()
    }

    fun viewData(){
        binding.vID.text = "ID : "+ sharedPreferences.getString("nim","")
        binding.vNama.text = "Nama : " + sharedPreferences.getString("nama", "")
    }

}