package com.example.mediadatabase

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import java.time.LocalDateTime

class AddMediaActivity : AppCompatActivity() {

    private lateinit var add : Button
    private lateinit var back : Button
    private lateinit var adView : AdView
    private lateinit var rb : RatingBar

    private lateinit var notes: EditText


    private lateinit var datePicker : DatePicker
    private lateinit var title: EditText
    private lateinit var location: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        add = findViewById<Button>(R.id.add_button)
        back = findViewById<Button>(R.id.back_button)
        rb = findViewById<RatingBar>(R.id.rating)
        rb.stepSize = .5f

        notes = findViewById<EditText>(R.id.notes)
        datePicker = findViewById<DatePicker>(R.id.date)
        title = findViewById<EditText>(R.id.title)
        location = findViewById<EditText>(R.id.location)


        rb.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener {rb, rating, fromUser ->
            var ratingVal = rating
            Log.w("MainActivity", "" + ratingVal)
        }

        add.setOnClickListener { update() }
        back.setOnClickListener { back() }




        adView = AdView(this)
        var adSize = AdSize(AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT)
        adView.setAdSize(adSize)
        var adUnitId = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId
        var builder = AdRequest.Builder()
        builder.addKeyword("media").addKeyword("movie").addKeyword("show")
        var adRequest = builder.build()
        var adLayout = findViewById<LinearLayout>(R.id.advertisement)
        adLayout.addView(adView)
        adView.loadAd(adRequest)




    }

    fun update() {
        val calendar = java.util.Calendar.getInstance()
        calendar.set(datePicker.year,datePicker.month,datePicker.dayOfMonth, 0, 0)
        val start= calendar.timeInMillis
        calendar.add(java.util.Calendar.HOUR, 1)
        val end = calendar.timeInMillis

        val intent = Intent(Intent.ACTION_EDIT).apply {
            type = "vnd.android.cursor.item/event"
            putExtra(CalendarContract.Events.TITLE, title.text.toString())
            putExtra(CalendarContract.Events.EVENT_LOCATION, location.text.toString())
            putExtra(CalendarContract.Events.DESCRIPTION,title.text.toString())
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            putExtra(CalendarContract.Events.ALL_DAY, true)
        }
        startActivity(intent)
    }

    fun back() {
        finish()
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }


}