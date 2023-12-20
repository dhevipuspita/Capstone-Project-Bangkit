package com.capstone.cuacatani.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cuacatani.R
import com.capstone.cuacatani.model.Plant
import com.capstone.cuacatani.ui.detail.DetailActivity

private const val ARG_PARAM2 = "param2"

class FruitFragment : Fragment() {
    private lateinit var adapter: SectionsPagerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var plantArrayList: ArrayList<Plant>

    private lateinit var imageId: Array<Int>
    private lateinit var plantId: Array<String>
    private lateinit var panenId: Array<String>
    private lateinit var plant: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fruit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rv_stories)
        recyclerView.layoutManager =layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = SectionsPagerAdapter(plantArrayList) { clickedPlant ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("PLANT_ID", clickedPlant.titlePlant)
            intent.putExtra("PANEN_ID", clickedPlant.titlePanen)
            intent.putExtra("IMAGE_ID", clickedPlant.titleImage)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
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
}