package com.example.fooddelivery.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.DetailsActivity
import com.example.fooddelivery.databinding.FragmentHomeBinding
import com.example.fooddelivery.databinding.HomeFoodItemBinding
import com.example.fooddelivery.models.PopularModel

class PopularAdapter(val context: Context, var   list: ArrayList<PopularModel>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    class PopularViewHolder(binding : HomeFoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val foodImage = binding.homeFoodImage
        val foodName = binding.homeFoodName
        val foodPrice = binding.homeFoodPrice

        val item = binding.root

    }

    fun updateList(newList: ArrayList<PopularModel>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.PopularViewHolder {
        val binding = HomeFoodItemBinding.inflate(LayoutInflater.from( context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val listModel = list[position]
        listModel.getFoodImage()?.let {holder.foodImage.setImageResource(it)}
        holder.foodName.text = listModel.getFoodName()
        holder.foodPrice.text = listModel.getFoodPrice()

        holder.item.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("foodImage", listModel.getFoodImage())
            intent.putExtra("foodName", listModel.getFoodName())

            context.startActivity(intent)

        }


     }
}