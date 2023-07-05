package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_mediu.button
import kotlinx.android.synthetic.main.content_mediu.button13
import kotlinx.android.synthetic.main.content_mediu.button14
import kotlinx.android.synthetic.main.content_mediu.button15
import kotlinx.android.synthetic.main.content_mediu.button16
import kotlinx.android.synthetic.main.content_mediu.button17
import kotlinx.android.synthetic.main.content_mediu.button18
import kotlinx.android.synthetic.main.content_mediu.button19


class ContentMainActivity3 : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var mediaPlayer: MediaPlayer? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_mediu)

        back = findViewById<Button>(R.id.back)
        back.setOnClickListener {

            val intent2 = Intent(this@ContentMainActivity3, MainActivity::class.java)
            startActivity(intent2)

        }

        val images = mutableListOf(
            yu1, yu2, yu3, yu4, yu5, yu6,cat,cow,dog,fox,
            yu1, yu2, yu3, yu4, yu5, yu6,cat,cow,dog,fox
        )

        val buttons = arrayOf(
           button ,button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12 , button13,
            button14,button15,button16,button17,button18,button19
        )

        val cartey = cartey
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var allCardsTurned = false

        images.shuffle()
        for (i in 0..19) {
            buttons[i].setBackgroundResource(cartey)
            buttons[i].text = "cartey"
            buttons[i].textSize = 0.0F
         buttons[i].setOnClickListener {
             if(buttons[i].text=="cartey" && !turnOver)
             {
                 buttons[i].setBackgroundResource(images[i])
                 buttons[i].setText(images[i])
                 clicked++
                 if(clicked==1)
                 {
                     lastClickedButton=buttons[i]
                     lastClickedImage=images[i].toString()
                 }
                 else if(clicked==2)
                 {
                     if(images[i].toString()==lastClickedImage)
                     {
                         buttons[i].isClickable=false
                         lastClickedButton.isClickable=false
                         matchedPairs++
                         if(matchedPairs==images.size/2)
                         {
                             mediaPlayer=MediaPlayer.create(this, R.raw.wow)
                             mediaPlayer?.setOnCompletionListener {
                                 Handler(Looper.getMainLooper()).postDelayed({
                                     Toast.makeText(this@ContentMainActivity3, "Victory!", Toast.LENGTH_SHORT).show()
                                 }, 100)

                             }
                             mediaPlayer?.start()
                             val intent = intent
                             finish()
                             startActivity(intent)
                         }
                     }
                     else
                     {
                         turnOver = true
                         Handler(Looper.getMainLooper()).postDelayed({
                             buttons[i].setBackgroundResource(cartey)
                             buttons[i].text = "cartey"
                             lastClickedButton.setBackgroundResource(cartey)
                             lastClickedButton.text = "cartey"
                             turnOver = false
                         }, 750)
                     }
                     clicked =0
                 }
             }
         }
        }
    }
}