package com.example.kotlinxml.activities

import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.kotlinxml.R
import com.example.kotlinxml.model.Product

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var backBtn: ImageButton
    private lateinit var cartBtn: ImageButton
    private lateinit var addBtn: TextView
    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView
    private lateinit var productReceive : Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)
        init()
        getDataFromIntent()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        backBtn = findViewById(R.id.back_btn)
        cartBtn = findViewById(R.id.cart)
        addBtn = findViewById(R.id.add_to_cart)
        productImage = findViewById(R.id.product_img)
        productName = findViewById(R.id.product_name)
        productPrice = findViewById(R.id.product_price)
        productDescription = findViewById(R.id.product_description)

        backBtn.setOnClickListener {
            finish()
        }

    }
    private fun getDataFromIntent() {
        productReceive = intent.getSerializableExtra("product") as Product
        productName.setText(productReceive.name)
        productPrice.setText(productReceive.price.toString() + "VNĐ")
        productDescription.setText(productReceive.description)
        Glide.with(this)
            .load(productReceive.img_url)
            .into(productImage)
    }
}
