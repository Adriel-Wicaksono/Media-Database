package com.example.mediadatabase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mediadatabase.AddMediaActivity
import com.example.mediadatabase.R

class MainActivity : AppCompatActivity() {

    private lateinit var enterButton : Button

    private lateinit var username: EditText
    private lateinit var media : Media
    private lateinit var light : Button
    private lateinit var dark : Button
    private var lightDarkFlag : Int = 0

    companion object {
        lateinit var database : Database
    }

    var primaryActivity: PrimaryActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var sp = getSharedPreferences("data", Context.MODE_PRIVATE)

        if(sp.getInt("LIGHTDARK", 0) == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        enterButton = findViewById<Button>(R.id.enter)
        username = findViewById<EditText>(R.id.user)

        enterButton.setOnClickListener { enter() }

        dark = findViewById<Button>(R.id.dark)
        light = findViewById<Button>(R.id.light)

        dark.setOnClickListener { darkMode() }
        light.setOnClickListener { lightMode() }

        var storedUsername = sp.getString("USERNAME", "")
        if(storedUsername != null && storedUsername.isNotEmpty()) {
            username.setText(storedUsername)
        }
    }

    fun darkMode() {
        lightDarkFlag = 1
        setPreferences(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun lightMode() {
        lightDarkFlag = 0
        setPreferences(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }


    fun setPreferences(context : Context) {
        var sp = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putString("USERNAME", username.text.toString())
        editor.putInt("LIGHTDARK", lightDarkFlag)
        editor.commit()
    }


    fun enter() {
        var name = username.text.toString()
        if (name.isNotEmpty()) {
            setPreferences(this)

            database = Database(name)
            val intent = Intent(this, PrimaryActivity::class.java)
            startActivity(intent)
        }
    }


}