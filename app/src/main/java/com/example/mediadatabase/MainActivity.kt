package com.example.mediadatabase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mediadatabase.AddMediaActivity
import com.example.mediadatabase.R
import com.example.mediadatabase.RemoveMediaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var loginButton : Button
    private lateinit var signUpButton : Button

    private lateinit var username: EditText
    private lateinit var media : Media
    private lateinit var light : Button
    private lateinit var dark : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        loginButton = findViewById<Button>(R.id.login)
        signUpButton = findViewById<Button>(R.id.signup)
        username = findViewById<EditText>(R.id.user)

        loginButton.setOnClickListener { loginUser() }
        signUpButton.setOnClickListener { signUpUser() }

        dark = findViewById<Button>(R.id.dark)
        light = findViewById<Button>(R.id.light)

        dark.setOnClickListener { darkMode() }
        light.setOnClickListener { lightMode() }

    }

    fun darkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun lightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }



    fun loginUser() {
        var name = loginButton.text.toString()
        media = Media(name)
        media.setPreferences(this)
        val intent = Intent(this, PrimaryActivity::class.java)
        startActivity(intent)
    }

    fun signUpUser() {
        var name = loginButton.text.toString()
        media = Media(name)
        media.setPreferences(this)
        val intent = Intent(this, PrimaryActivity::class.java)
        startActivity(intent)
    }


}