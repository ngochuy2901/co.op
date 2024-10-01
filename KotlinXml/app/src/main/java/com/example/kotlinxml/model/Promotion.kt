package com.example.kotlinxml.model

import java.util.Date

class Promotion {
    lateinit var title: String
    lateinit var date: Date
    lateinit var description: String

    constructor(title: String, date: Date, description: String) {
        this.title = title
        this.date = date
        this.description = description
    }

    constructor()

}