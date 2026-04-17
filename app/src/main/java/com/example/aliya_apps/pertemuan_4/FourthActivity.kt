package com.example.aliya_apps.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aliya_apps.MainActivity
import com.example.aliya_apps.R
import com.example.aliya_apps.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("nama")
        val from = intent.getStringExtra("asal")
        val age = intent.getIntExtra("usia",0)
        Log.e("Data Intent","Nama: $name , Usia: $age, Asal: $from")
        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Kembali"){
                    finish()
                    Log.e("Info Snackbar","Snackbar ditutup")
                }
                .show()
        }

          binding.btnShowAlertDialog.setOnClickListener {
              MaterialAlertDialogBuilder(this)
                  .setTitle("Konfirmasi")
                  .setMessage("Apakah Anda yakin ingin melanjutkan?")
                  .setPositiveButton("Ya") { dialog, _ ->
                      dialog.dismiss()
                      Log.e("Info Dialog","Anda memilih Ya!")
                  }
                  .setNegativeButton("Batal") { dialog, _ ->
                      dialog.dismiss()
                      Log.e("Info Dialog","Anda memilih Tidak!")
                  }
                  .show()
          }


        binding.btnForMain.setOnClickListener {
            finish()
        }

        Log.e("onCreate", "FourthActivity dibuat pertama kali")
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }
}