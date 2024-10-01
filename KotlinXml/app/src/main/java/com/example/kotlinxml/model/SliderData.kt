
package com.example.kotlinxml.model

class SliderData {
    var img_url: String

    // Constructor mặc định
    constructor() {
        this.img_url = ""
    }

    // Constructor với tham số
    constructor(img_url: String) {
        this.img_url = img_url
    }
}
