package com.example.mediadatabase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mediadatabase.AddMediaActivity
import com.example.mediadatabase.R
import com.example.mediadatabase.RemoveMediaActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton : Button
    private lateinit var signUpButton : Button

    private lateinit var username: EditText

    private lateinit var sName: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        loginButton = findViewById<Button>(R.id.login)
        signUpButton = findViewById<Button>(R.id.signup)
        username = findViewById<EditText>(R.id.user)

        loginButton.setOnClickListener { loginUser() }
        signUpButton.setOnClickListener { signUpUser() }

    }

    fun setPreferences(context: Context){
        val sp: SharedPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sp.edit()
        sName = username.text.toString()
        editor.putString("username", sName)
        editor.commit()
    }

    fun loginUser() {
        setPreferences(this)
        val intent = Intent(this, AddMediaActivity::class.java)
        startActivity(intent)
    }

    fun signUpUser() {
        setPreferences(this)
        val intent = Intent(this, RemoveMediaActivity::class.java)
        startActivity(intent)
    }


}