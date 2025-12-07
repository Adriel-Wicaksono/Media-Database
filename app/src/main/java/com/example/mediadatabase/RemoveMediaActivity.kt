package com.example.mediadatabase

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RemoveMediaActivity : AppCompatActivity() {

    private lateinit var remove : Button
    private lateinit var back : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_remove)

        remove = findViewById<Button>(R.id.remove_button)
        back = findViewById<Button>(R.id.remove_back_button)

        remove.setOnClickListener { update() }
        back.setOnClickListener { back() }



    }

    fun update() {

    }

    fun back() {
        finish()
    }


}