package com.example.mediadatabase

<<<<<<< HEAD
class Media {

    private var title : String = ""
    private var location : String = ""
    private var rating : Float = 0.0f

    constructor(title : String, location : String, rating : Float) {
        this.title = title
        this.location = location
        this.rating = rating
=======
import android.content.Context

class Media {

    private var username : String = ""

    constructor(username : String) {
        this.username = username
    }

    fun setPreferences(context : Context) {
        var sp = context.getSharedPreferences("username", Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putString("USERNAME", username)
        editor.commit()
>>>>>>> 0f5b309 (Updated transition screens and persistent data)
    }


}