package com.example.aliya_apps.Home.pertemuan_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_apps.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Pertemuan 13"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val adapter = ThirteenthTabsAdapter(this)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->

            when(position){

                0 -> tab.text = "Capture"

                1 -> tab.text = "Scan"

                2 -> tab.text = "QRCode"

            }

        }.attach()

    }
}