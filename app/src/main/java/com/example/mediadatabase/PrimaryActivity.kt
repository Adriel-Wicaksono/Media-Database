package com.example.mediadatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PrimaryActivity : AppCompatActivity() {

    private lateinit var addMediaButton : Button
    private lateinit var removeMediaButton : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_primary)

        addMediaButton = findViewById<Button>(R.id.add_media)
        removeMediaButton = findViewById<Button>(R.id.remove_media)

        addMediaButton.setOnClickListener { addMedia() }
        removeMediaButton.setOnClickListener { removeMedia() }

    }

    fun addMedia() {
        var intent = Intent(this, AddMediaActivity::class.java)
        startActivity(intent)
    }

    fun removeMedia() {
        var intent = Intent(this, RemoveMediaActivity::class.java)
        startActivity(intent)
    }







}