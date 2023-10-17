package com.example.camera_monitoring.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.camera_monitoring.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Камеры"
                1 -> tab.text = "Комнаты"
            }
        }.attach()
    }
}