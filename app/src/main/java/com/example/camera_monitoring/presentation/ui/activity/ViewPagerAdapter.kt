package com.example.camera_monitoring.presentation.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.camera_monitoring.presentation.ui.fragment.cameras.CamerasFragment
import com.example.camera_monitoring.presentation.ui.fragment.doors.DoorsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CamerasFragment()
            1 -> DoorsFragment()
            else -> CamerasFragment()
        }
    }
}