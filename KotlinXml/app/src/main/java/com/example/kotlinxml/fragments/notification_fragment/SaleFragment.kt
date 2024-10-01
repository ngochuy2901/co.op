package com.example.kotlinxml.fragments.notification_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinxml.R
import com.example.kotlinxml.adapter.PromotionAdapter
import com.example.kotlinxml.repository.PromotionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SaleFragment : Fragment() {
    private lateinit var promotionRecyclerView : RecyclerView
    private lateinit var promotionAdapter : PromotionAdapter
    private lateinit var promotionRepository: PromotionRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(R.layout.fragment_sale, container, false)
        promotionRecyclerView = view.findViewById(R.id.promotion_recycle_view)
        promotionRepository = PromotionRepository()
        fetchPromotions()
        return view
    }
    private fun fetchPromotions() {
        CoroutineScope(Dispatchers.Main).launch {
            val promotions = promotionRepository.getAllPromotion{ promotions ->
                promotionAdapter = PromotionAdapter(promotions)
                promotionRecyclerView.layoutManager = LinearLayoutManager(context)
                promotionRecyclerView.adapter = promotionAdapter
            }

        }
    }
}