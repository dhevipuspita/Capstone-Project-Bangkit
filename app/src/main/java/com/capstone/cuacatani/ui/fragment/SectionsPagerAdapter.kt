package com.capstone.cuacatani.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.cuacatani.databinding.BoxPlantsBinding
import com.capstone.cuacatani.model.Plant

class SectionsPagerAdapter(private val newsList: ArrayList<Plant>) :
    RecyclerView.Adapter<SectionsPagerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BoxPlantsBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.ivPhoto
        val plant = binding.tvNamePlant
        val panen = binding.tvPanen
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BoxPlantsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = newsList[position]

        holder.image.setImageResource(list.titleImage)
        holder.plant.text = list.titlePlant
        holder.panen.text = list.titlePanen
    }


    override fun getItemCount(): Int {
        return newsList.size
    }
}