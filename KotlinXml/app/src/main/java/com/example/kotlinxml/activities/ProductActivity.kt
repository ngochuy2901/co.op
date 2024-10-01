package com.example.kotlinxml.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinxml.R
import com.example.kotlinxml.adapter.ProductAdapter
import com.example.kotlinxml.model.Product
import com.example.kotlinxml.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {
    private lateinit var productRecyclerView : RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productRepository: ProductRepository
//    private lateinit var title : TextView
    private lateinit var backBtn : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
        fetchProduct()
    }

    private fun init() {
        productRecyclerView = findViewById(R.id.product_recycle_view)
        productRepository = ProductRepository()
        backBtn = findViewById(R.id.back_btn)
        backBtn.setOnClickListener {
            finish()
        }

    }

    //hiển thị sản phẩm
    private fun fetchProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            val product = productRepository.getAllProduct{ products ->
                productAdapter = ProductAdapter(products, this@ProductActivity)
                productRecyclerView.layoutManager = GridLayoutManager(this@ProductActivity, 2)
                productRecyclerView.adapter = productAdapter
            }

        }
    }

    override fun onItemClick(position: Int) {
        startActivity(Intent(this, ProductDetailActivity::class.java))
    }
}