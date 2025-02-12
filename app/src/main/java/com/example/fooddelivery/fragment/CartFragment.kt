package com.example.fooddelivery.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.Details
import com.example.fooddelivery.DetailsActivity
import com.example.fooddelivery.adapters.CartAdapter
import com.example.fooddelivery.databinding.FragmentCartBinding
import com.example.fooddelivery.models.PopularModel
import com.example.fooddelivery.models.SharedModel


class CartFragment : Fragment() {
    private lateinit var binding : FragmentCartBinding

    //private lateinit var list : ArrayList<PopularModel>
    private lateinit var adapter : CartAdapter

    private lateinit var sharedModel : SharedModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)


        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel::class.java)

        adapter = CartAdapter(requireContext(), sharedModel.cartItem.value ?: ArrayList())
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRv.adapter = adapter

        binding.proceedBtn.setOnClickListener{
            val totalPrice = sharedModel.cartItem.value?.let { it1 -> calPrice(it1) }
            if (totalPrice == 0 ){
                Toast.makeText(requireContext(), "List is empty", Toast.LENGTH_SHORT).show()
            }
            val TotalPrice = totalPrice.toString()
            val intent = Intent(requireContext(), Details::class.java)
            intent.putExtra("totalPrice", TotalPrice)
            startActivity(intent)
        }
        return binding.root
    }

    fun calPrice(itemPrices:List<PopularModel>):Int{

        var totalPrice = 0

        itemPrices.forEach{ itemPrice ->
            val price = itemPrice.getFoodPriceConstant() * itemPrice.getFoodCount()
            totalPrice += price
        }

        return totalPrice
    }
}