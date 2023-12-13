package com.capstone.cuacatani

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.capstone.cuacatani.databinding.ActivityMainBinding
import com.capstone.cuacatani.ui.fragment.CornFragment
import com.capstone.cuacatani.ui.fragment.FruitFragment
import com.capstone.cuacatani.ui.fragment.SectionsPagerAdapter
import com.capstone.cuacatani.ui.fragment.VegFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout

    private val cornFragment = CornFragment()
    private val vegFragment = VegFragment()
    private val fruitFragment = FruitFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabs

        tabLayout.addTab(tabLayout.newTab().setText("Corn"))
        tabLayout.addTab(tabLayout.newTab().setText("Vegetable"))
        tabLayout.addTab(tabLayout.newTab().setText("Fruit"))

        supportActionBar?.elevation = 0f
        setDefaultTab(CornFragment())
        setOnTabClickedListener(CornFragment())
    }

    private fun setDefaultTab(fragment: CornFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }


    private fun setOnTabClickedListener(fragment: CornFragment) {
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedFragment = when (tab?.position) {
                    0 -> cornFragment
                    1 -> vegFragment
                    2 -> fruitFragment
                    else -> cornFragment
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}
