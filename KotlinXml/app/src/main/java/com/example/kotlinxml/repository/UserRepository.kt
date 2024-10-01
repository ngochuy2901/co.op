package com.example.kotlinxml.repository

import com.example.kotlinxml.model.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getUserInfo(userId: String, callback: (User?) -> Unit) {
        // Truy vấn vào bộ sưu tập "users" để lấy thông tin người dùng dựa trên userId
        firestore.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Chuyển đổi dữ liệu từ Firestore thành đối tượng User
                    val user = document.toObject(User::class.java)
                    callback(user)  // Trả về đối tượng User qua callback
                } else {
                    callback(null)  // Trả về null nếu không tìm thấy người dùng
                }
            }
            .addOnFailureListener { exception ->
                // Xử lý lỗi khi truy vấn Firestore thất bại
                callback(null)
            }
    }
}