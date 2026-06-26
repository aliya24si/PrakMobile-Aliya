package com.example.aliya_apps.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aliya_apps.R
import com.example.aliya_apps.databinding.ActivityThirdBinding
import com.example.aliya_apps.utils.NotificationHelper
import com.example.aliya_apps.utils.PermissionHelper

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.btnKirim.setOnClickListener {
            //Mengambil value dari inputNama dan menampilkan di Logcat
            val nama = binding.inputNoTujuan.text
            Log.e("Klik btnKirim","Tombol berhasil di tekan. Isi dari inputNama = $nama")

            Toast.makeText(this, "Pesan berhasil dikirim ke : $nama" , Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ThirdResultActivity::class.java)
//            startActivity(intent)

            binding.btnKirim.setOnClickListener {
                val noTujuan = binding.inputNoTujuan.text
                val intent = Intent(this, ThirdResultActivity::class.java)

                //startActivity(intent)

                NotificationHelper.showNotification(
                    this, //Jika panggil di fragment maka requireContext()
                    "Pesanan Anda",
                    "Halo $noTujuan, Pesanan Anda Sedang Diproses",
                    intent
                )
            }
        }
    }
}