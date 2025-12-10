package com.example.mediadatabase

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.MainScope
import java.util.Locale

class PrimaryActivity : AppCompatActivity() {

    private lateinit var addMediaButton : Button
    private lateinit var removeMediaButton : Button
    private lateinit var mediaLayout : GridLayout
    private lateinit var scrollView : ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_primary)

        MainActivity.database.setUIUpdateFunc { populateMedia() }

        addMediaButton = findViewById<Button>(R.id.add_media)
        removeMediaButton = findViewById<Button>(R.id.remove_media)
        mediaLayout = findViewById<GridLayout>(R.id.displayUserMedia)
        scrollView = findViewById<ScrollView>(R.id.scroll)

        populateMedia()

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

    override fun onResume() {
        super.onResume()
        populateMedia()
    }

    fun populateMedia() {
        mediaLayout.removeAllViews()

        for (media in MainActivity.database.mediaList) {
            Log.w("PrimaryActivity", "name is " + media.title)

            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.setPadding(10, 5, 10, 5)
            linearLayout.gravity = Gravity.CENTER_HORIZONTAL
            linearLayout.background = getDrawable(R.drawable.media_item_background)

            val tView = TextView(this)
            val date: String = SimpleDateFormat(
                "MMM dd, yyyy",
                Locale.getDefault()
            ).format(media.dateWatched)
            tView.text = media.title + "\n" + date + "\n" + media.where + "\n"
            tView.textSize = 14f
            tView.gravity = Gravity.CENTER
            linearLayout.addView(tView)

            val ratingBar = RatingBar(this)
            ratingBar.stepSize = 0.5f
            if (media.rating == null) {
                ratingBar.rating = 0f
            } else {
                ratingBar.rating = media.rating!!
            }
            ratingBar.setIsIndicator(true)
            linearLayout.addView(ratingBar)

            val note = TextView(this)
            note.text = media.note
            note.textSize = 14f
            note.gravity = Gravity.CENTER
            linearLayout.addView(note)

            val params = GridLayout.LayoutParams().apply {
                width = GridLayout.LayoutParams.WRAP_CONTENT
                height = GridLayout.LayoutParams.WRAP_CONTENT
                setGravity(Gravity.CENTER_HORIZONTAL)
                setMargins(0, topMargin, 0, 5)

            }

            mediaLayout.addView(linearLayout, params)
        }
    }
}
