package com.example.kotlinxml.repository

import com.example.kotlinxml.model.Promotion
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject


class PromotionRepository {
    private var firestore = FirebaseFirestore.getInstance()
    private lateinit var listPromotion: MutableList<Promotion>

    // lấy danh sách khuyến mãi
    fun getAllPromotion(callback: (List<Promotion>) -> Unit) {
        listPromotion = mutableListOf()
        firestore.collection("promotions")
            .get()
            .addOnSuccessListener { result ->
                val promotions = result.map { document ->
                    document.toObject<Promotion>()
                }
                listPromotion.addAll(promotions)
                callback(listPromotion)  // Chỉ gọi callback sau khi đã xử lý xong tất cả
            }
    }
}