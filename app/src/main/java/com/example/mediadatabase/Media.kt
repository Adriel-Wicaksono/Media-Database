package com.example.mediadatabase

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
    }


}