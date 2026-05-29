package com.example.aliya_apps.Home.pertemuan_10

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TenthTabsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    // Menentukan jumlah total tab yang kamu miliki (yaitu 3: Tab A, Tab B, Tab C)
    override fun getItemCount(): Int = 3

    // Menentukan Fragment mana yang dipanggil pada setiap urutan/posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment() // Gantilah dengan class Fragment Tab A kamu yang asli
            1 -> TabBFragment() // Gantilah dengan class Fragment Tab B kamu yang asli
            2 -> TabCFragment() // Ini memanggil TabCFragment yang berisi daftar produk
            else -> TabAFragment()
        }
    }
}