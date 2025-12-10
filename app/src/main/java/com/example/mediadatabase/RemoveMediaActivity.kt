package com.example.mediadatabase

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout

class RemoveMediaActivity : AppCompatActivity() {

    private lateinit var scroll: ScrollView
    private lateinit var radios: RadioGroup
    private lateinit var back: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = RelativeLayout(this)

        scroll = ScrollView(this)
        radios = RadioGroup(this)
        scroll.addView(radios)
        val scrollParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        scrollParams.topMargin = 205
        layout.addView(scroll, scrollParams)

        back = Button(this)
        back.text = "BACK"
        back.setOnClickListener { finish() }
        val backParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        backParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        backParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        backParams.bottomMargin = 80
        layout.addView(back, backParams)

        setContentView(layout)
        updateRadioButtons()
    }

    private fun updateRadioButtons() {
        radios.removeAllViews()
        for (media in MainActivity.database.mediaList.toList()) {
            val radio = RadioButton(this)
            radio.text = media.title
            radio.setOnClickListener {
                MainActivity.database.mediaList.remove(media)
                updateRadioButtons()
            }
            radios.addView(radio)
        }
    }
}
