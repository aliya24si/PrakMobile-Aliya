package com.example.aliya_apps.Home.pertemuan_10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aliya_apps.R
import com.example.aliya_apps.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator // Import tambahan untuk menghubungkan Tab & ViewPager

class TenthActivity : AppCompatActivity() {

    // 1. Inisialisasi View Binding
    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Menggunakan View Binding untuk set content view
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Atur Window Insets (Edge-to-Edge) agar tidak menabrak tombol navigasi bawah
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Hanya berikan padding kiri, kanan, dan bawah.
            // Bagian atas (top = 0) sengaja dikosongkan agar Toolbar menyatu cantik dengan Status Bar.
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Set Toolbar sebagai ActionBar halaman ini
        setSupportActionBar(binding.toolbar)

        // 5. Berikan aksi klik pada tombol back di Toolbar
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Menutup activity ini dan kembali ke HomeFragment
        }

        // TAMBAHAN BARU: SETUP TAB & VIEWPAGER2

        // 6. Pasang Adapter ke ViewPager2
        // 1. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home_2)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Tab B"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home_2)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> { // TAMBAHAN BARU: Konfigurasi Tab C
                    tab.text = "Tab C"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home_2)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 99 // Contoh badge dengan angka baru
                }
            }
        }.attach()
    }
}