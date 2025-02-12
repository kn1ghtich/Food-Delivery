package com.example.fooddelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.adapters.PopularAdapter
import com.example.fooddelivery.models.PopularModel
import com.example.fooddelivery.models.SharedModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var adapter: PopularAdapter
    private lateinit var menuList: ArrayList<PopularModel>
    private lateinit var menu_RV: RecyclerView
    private lateinit var sharedModel : SharedModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_menu_bottom_sheer, container, false)
        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel::class.java)


        menuList = java.util.ArrayList()
        menuList.add(PopularModel(R.drawable.pop_menu_burger, "Sandowitch", 7,7,1))
        menuList.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", 9,9,1))
        menuList.add(PopularModel(R.drawable.pop_menu_momo, "Burger", 5,5,1))

        adapter = PopularAdapter(requireContext(), menuList)
        adapter.setSharedModel(sharedModel)
        menu_RV = view.findViewById(R.id.menu_RV)
        menu_RV.layoutManager = LinearLayoutManager(requireContext())
        menu_RV.adapter = adapter


        return view

    }


}