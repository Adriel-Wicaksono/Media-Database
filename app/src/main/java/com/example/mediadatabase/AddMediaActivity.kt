package com.example.mediadatabase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class AddMediaActivity : AppCompatActivity() {

    private lateinit var add : Button
    private lateinit var back : Button
    private lateinit var adView : AdView
    private lateinit var rb : RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        add = findViewById<Button>(R.id.add_button)
        back = findViewById<Button>(R.id.add_back_button)
        rb = findViewById<RatingBar>(R.id.rating)
        rb.stepSize = .5f


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