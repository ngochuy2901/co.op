package com.example.kotlinxml.repository

import com.example.kotlinxml.model.Product
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class ProductRepository {
    private var firestore = FirebaseFirestore.getInstance()
    private lateinit var listProduct: MutableList<Product>

    // lấy danh sách sản phẩm
    fun getAllProduct(callback: (List<Product>) -> Unit) {
        listProduct = mutableListOf()
        firestore.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val products = result.map { document ->
                    document.toObject<Product>()
                }
                listProduct.addAll(products)
                callback(listProduct)  // Chỉ gọi callback sau khi đã xử lý xong tất cả
            }
            .addOnFailureListener(OnFailureListener {

            })
    }
}