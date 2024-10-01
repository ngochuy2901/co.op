package com.example.kotlinxml.model

import java.io.Serializable

class Product : Serializable {
    lateinit var category_id: String
    lateinit var name: String
    lateinit var description: String
    lateinit var img_url: String
    var price: Long = 0
    var rating: Double = 0.0
    var stock: Long = 0


    constructor(
        category_id: String,
        name: String,
        description: String,
        img_url: String,
        price: Long,
        rating: Double,
        stock: Long
    ) {
        this.category_id = category_id
        this.name = name
        this.description = description
        this.img_url = img_url
        this.price = price
        this.rating = rating
        this.stock = stock
    }

    constructor()
}