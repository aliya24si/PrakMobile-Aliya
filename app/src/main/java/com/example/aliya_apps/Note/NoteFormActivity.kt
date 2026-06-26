package com.example.aliya_apps.Note

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.aliya_apps.MainActivity // PASTIKAN SESUAIKAN dengan nama Activity Utama Anda
import com.example.aliya_apps.R
import com.example.aliya_apps.data.AppDatabase
import com.example.aliya_apps.data.entity.NoteEntity
import com.example.aliya_apps.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch


class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    private val CHANNEL_ID = "note_notification_channel"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.noteDao().insert(note)

                    // 1. Tampilkan notifikasi
                    showNotification(title)

                    // 2. Kosongkan inputan sebagai tanda berhasil (TIDAK MENGGUNAKAN finish() LAGI)
                    binding.etTitle.text?.clear()
                    binding.etContent.text?.clear()
                    Toast.makeText(this@NoteFormActivity, "Catatan disimpan! Cek notifikasi Anda.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showNotification(noteTitle: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Catatan Baru",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifikasi saat berhasil menambahkan catatan"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Agar ketika diklik dia membuka MainActivity (yang berisi NoteFragment)
        // dan membersihkan halaman form yang sedang terbuka saat ini.
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            pendingIntentFlags
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Menggunakan icon bawaan android agar aman dari error resource
            .setContentTitle("Catatan Berhasil Disimpan")
            .setContentText("Klik di sini untuk melihat catatan '$noteTitle'.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}