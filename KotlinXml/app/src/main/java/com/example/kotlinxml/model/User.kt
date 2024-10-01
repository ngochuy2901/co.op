package com.example.kotlinxml.model

class User {
    private lateinit var userId : String
    private lateinit var fullName : String
    private lateinit var email : String
    private lateinit var phoneNumber : String
    private lateinit var avataUrl : String
    private lateinit var address : String


    constructor(
        userId: String,
        fullName: String,
        email: String,
        phoneNumber: String,
        avataUrl: String,
        address: String
    ) {
        this.userId = userId
        this.fullName = fullName
        this.email = email
        this.phoneNumber = phoneNumber
        this.avataUrl = avataUrl
        this.address = address
    }

    constructor()

    // Getter cho các thuộc tính
    fun getUserId(): String = userId
    fun getFullName(): String = fullName
    fun getEmail(): String = email
    fun getPhoneNumber(): String = phoneNumber
    fun getAvataUrl(): String = avataUrl
    fun getAddress(): String = address

}