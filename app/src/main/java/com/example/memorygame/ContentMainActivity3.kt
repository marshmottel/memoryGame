package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main_activity3.*
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main_activity3.tryAgain2
import kotlinx.android.synthetic.main.content_main_activity3.button1
import kotlinx.android.synthetic.main.content_main_activity3.button10
import kotlinx.android.synthetic.main.content_main_activity3.button11
import kotlinx.android.synthetic.main.content_main_activity3.button12
import kotlinx.android.synthetic.main.content_main_activity3.button2
import kotlinx.android.synthetic.main.content_main_activity3.button3
import kotlinx.android.synthetic.main.content_main_activity3.button4
import kotlinx.android.synthetic.main.content_main_activity3.button5
import kotlinx.android.synthetic.main.content_main_activity3.button6
import kotlinx.android.synthetic.main.content_main_activity3.button7
import kotlinx.android.synthetic.main.content_main_activity3.button8
import kotlinx.android.synthetic.main.content_main_activity3.button9
import kotlinx.android.synthetic.main.content_main_activity3.button
import kotlinx.android.synthetic.main.content_main_activity3.button13
import kotlinx.android.synthetic.main.content_main_activity3.button14
import kotlinx.android.synthetic.main.content_main_activity3.button15
import kotlinx.android.synthetic.main.content_main_activity3.button16
import kotlinx.android.synthetic.main.content_main_activity3.button17
import kotlinx.android.synthetic.main.content_main_activity3.button18
import kotlinx.android.synthetic.main.content_main_activity3.button19


class ContentMainActivity3 : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var tryAgainButton2:Button
    private var remainingTime2 = 60
    private lateinit var textViewRemainingTime2: TextView
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var mediaPlayer: MediaPlayer? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main_activity3)

        textViewRemainingTime2 = findViewById(R.id.textViewRemainingTime2)
        back = findViewById<Button>(R.id.back)
        back.setOnClickListener {
            val intent2 = Intent(this@ContentMainActivity3, MainActivity::class.java)
            startActivity(intent2)
        }

        tryAgainButton2 = findViewById(R.id.tryAgain2)
        tryAgainButton2.setOnClickListener {
            val intentTryAgain2=Intent(this@ContentMainActivity3, ContentMainActivity3::class.java)
            startActivity(intentTryAgain2)
        }
        val images = mutableListOf(
            yu1, yu2, yu3, yu4, yu5, yu6,yu7,yu8,yu9,yu10,
            yu1, yu2, yu3, yu4, yu5, yu6,yu7,yu8,yu9,yu10,
        )

        val buttons = arrayOf(
           button ,button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12 , button13,
            button14,button15,button16,button17,button18,button19
        )

        val cartey = cartey
        var clicked = 0
        var turnOver = false
        //var lastClicked = -1
        //var allCardsTurned = false

        val handler = Handler()
        val timerRunnable = object : Runnable {
            override fun run() {
                remainingTime2--
                // Actualizați timpul rămas în TextView
                textViewRemainingTime2.text = remainingTime2.toString()


                // Verificați dacă timpul a expirat
                if (remainingTime2 <= 0) {
                    // Afisați "Game Over" sau executați acțiunile corespunzătoare
                    Toast.makeText(this@ContentMainActivity3, "Game Over", Toast.LENGTH_SHORT).show()
                    for (button in buttons) {
                        button.isClickable = false
                    }
                    tryAgain2.isClickable=true
                    tryAgain2.visibility= View.VISIBLE


// Activează butonul "QUIT"
                    back.isClickable = true
                    // ...
                    return
                }

                // Programați următoarea actualizare a timerului peste 1 secundă
                handler.postDelayed(this, 1000)

            }
        }
        textViewRemainingTime2.text = remainingTime2.toString()
// Porniți timerul
        handler.postDelayed(timerRunnable, 1000)



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