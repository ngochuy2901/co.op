package com.example.kotlinxml.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinxml.R
import com.example.kotlinxml.activities.ProductDetailActivity
import com.example.kotlinxml.model.Product

class ProductAdapter(
    private val products: List<Product>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.product_name)
        val productPrice: TextView = view.findViewById(R.id.product_price)
        val productImg : ImageView = view.findViewById(R.id.product_img)
        //onclick
        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, listener)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.name ?: "Không có tên"
        holder.productPrice.text = product.price.toString()
        Glide.with(holder.itemView.context)
            .load(product.img_url)
            .into(holder.productImg)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java)

            // Truyền đối tượng sản phẩm qua intent
            intent.putExtra("product", product)
            // Bắt đầu ProductDetailActivity
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return products.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}