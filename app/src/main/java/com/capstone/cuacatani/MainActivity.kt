package com.capstone.cuacatani

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.capstone.cuacatani.ui.fragment.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_corn,
            R.string.tab_veg,
            R.string.tab_fruit
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab selected event
                tab?.let {
                    viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselected event
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselected event
            }
        })

        supportActionBar?.elevation = 0f
    }
}
