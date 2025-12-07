package com.example.mediadatabase

class Media {

    private var title : String = ""
    private var location : String = ""
    private var rating : Float = 0.0f

    constructor(title : String, location : String, rating : Float) {
        this.title = title
        this.location = location
        this.rating = rating
    }


}