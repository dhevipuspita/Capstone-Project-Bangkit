package com.capstone.cuacatani.ui.plants

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cuacatani.R
import com.capstone.cuacatani.databinding.ActivityPlantsBinding
import com.capstone.cuacatani.model.Plant
import com.capstone.cuacatani.ui.about.AboutActivity
import com.capstone.cuacatani.ui.detail.DetailActivity
import com.capstone.cuacatani.ui.fragment.SectionsPagerAdapter
import com.capstone.cuacatani.ui.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlantsActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SectionsPagerAdapter
    private lateinit var plantArrayList: ArrayList<Plant>
    private lateinit var binding: ActivityPlantsBinding

    private lateinit var imageId: Array<Int>
    private lateinit var plantId: Array<String>
    private lateinit var panenId: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataInitialize()
        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager

        recyclerView = binding.rvList
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = SectionsPagerAdapter(plantArrayList) { clickedPlant ->
            val intent = Intent(this@PlantsActivity, DetailActivity::class.java)
            intent.putExtra("PLANT_ID", clickedPlant.titlePlant)
            intent.putExtra("PANEN_ID", clickedPlant.titlePanen)
            intent.putExtra("IMAGE_ID", clickedPlant.titleImage)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        bindUIComponents()

        // Select Search Activity by default
        bottomNavigationView.selectedItemId = R.id.navigation_plant

        handleTabButtonPress()
        supportActionBar?.hide()
    }

    private fun dataInitialize(){
        plantArrayList = arrayListOf<Plant>()

        imageId = arrayOf(
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato,
            R.drawable.tomato
        )

        plantId = arrayOf(
            getString(R.string.plant_1),
            getString(R.string.plant_2),
            getString(R.string.plant_3),
            getString(R.string.plant_4),
            getString(R.string.plant_5),
            getString(R.string.plant_6),
            getString(R.string.plant_7),
            getString(R.string.plant_8),
            getString(R.string.plant_9),
            getString(R.string.plant_10)
        )

        panenId = arrayOf(
            getString(R.string.panen_1),
            getString(R.string.panen_2),
            getString(R.string.panen_3),
            getString(R.string.panen_4),
            getString(R.string.panen_5),
            getString(R.string.panen_6),
            getString(R.string.panen_7),
            getString(R.string.panen_8),
            getString(R.string.panen_9),
            getString(R.string.panen_10)
        )

        for (i in imageId.indices){
            val plant = Plant(imageId[i], plantId[i], panenId[i])
            plantArrayList.add(plant)
        }
    }

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.nav_view)
    }
    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_plant -> {
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_about -> {
                    loadActivities(AboutActivity())
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }

    private fun loadActivities(activity: AppCompatActivity) {
        val intent = Intent(applicationContext, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

}