package com.example.kotlinxml.auth

import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAuthentication {
    private lateinit var firebaseAuth: FirebaseAuth;
    private lateinit var firestore: FirebaseFirestore

    constructor() {
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
    }

    fun register(
        email: String,
        password: String,
        fullName: String,
        phoneNumber: String,
        address: String,
        callback: (Boolean) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                callback(true)
                val userId: String = firebaseAuth.currentUser!!.uid
                saveUserToFirestore(userId, email, fullName, phoneNumber, address)
            }
            .addOnFailureListener(OnFailureListener {
                callback(false)
            })
    }

    private fun saveUserToFirestore(
        userId: String,
        email: String,
        fullName: String,
        phoneNumber: String,
        address: String
    ) {
        val userMap = hashMapOf(
            "userId" to userId,
            "email" to email,
            "fullName" to fullName,
            "phoneNumber" to phoneNumber,
            "address" to address,
            "avataUrl" to ""
        )

        // Thêm thông tin người dùng vào bộ sưu tập "users"
        firestore.collection("users")
            .document(userId)  // Đặt document với userId để định danh người dùng
            .set(userMap)
    }

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener(OnFailureListener {
                callback(false)
            })

    }

    fun isEmailRegistered(email: String, callback: (Boolean) -> Unit) {
        firebaseAuth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods != null && signInMethods.isNotEmpty()) {
                        // Email đã được sử dụng
                        callback(true)
                    } else {
                        // Email chưa được sử dụng
                        callback(false)
                    }
                } else {
                    callback(false)
                }
            }
    }

    fun isAuthenticated(): Boolean {
        val user = firebaseAuth.currentUser
        if (user != null) {
            return true
        } else {
            return false
        }
    }

    fun getUid(): String {
        return firebaseAuth.currentUser!!.uid
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}