package com.example.fooddelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.R
import com.example.fooddelivery.adapters.PopularAdapter
import com.example.fooddelivery.databinding.FragmentSearchBinding
import com.example.fooddelivery.models.PopularModel


class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var listPopular: ArrayList<PopularModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        listPopular = ArrayList()
        listPopular.add(PopularModel(R.drawable.pop_menu_burger, "Sandowitch", "$7"))
        listPopular.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "$9"))
        listPopular.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "$5"))



        popularAdapter = PopularAdapter(requireContext(), listPopular)

        binding.searchMenuRv.layoutManager = LinearLayoutManager(requireContext())
        binding.searchMenuRv.adapter = popularAdapter

        searchMenuFood()

        return binding.root


    }

    private fun searchMenuFood(){
        binding.searchMenuItem.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filteredList(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredList(newText)
                return true
            }
        })
    }

    private fun filteredList (input : String?){
        val filteredList = if (input.isNullOrEmpty()){
            listPopular
        }else{
            listPopular.filter { item ->
                item.getFoodName().contains(input, true)
            }
        }

        popularAdapter.updateList(filteredList as ArrayList<PopularModel>)

    }
}