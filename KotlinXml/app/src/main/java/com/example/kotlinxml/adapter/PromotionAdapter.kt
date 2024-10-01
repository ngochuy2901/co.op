package com.example.kotlinxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinxml.R
import com.example.kotlinxml.model.Promotion

class PromotionAdapter(private val promotions: List<Promotion>) : RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {

    class PromotionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.findViewById(R.id.promotion_title)
        val textViewDescription: TextView = view.findViewById(R.id.promotion_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return PromotionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        val promotion = promotions[position]
        holder.textViewTitle.text = promotion.title  // Giả sử bạn có thuộc tính title trong model Promotion
        holder.textViewDescription.text = promotion.description  // Giả sử bạn có thuộc tính description
    }

    override fun getItemCount(): Int {
        return promotions.size
    }
}