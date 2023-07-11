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
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main_activity3a.button
import kotlinx.android.synthetic.main.content_main_activity3a.button1
import kotlinx.android.synthetic.main.content_main_activity3a.button10
import kotlinx.android.synthetic.main.content_main_activity3a.button11
import kotlinx.android.synthetic.main.content_main_activity3a.button12
import kotlinx.android.synthetic.main.content_main_activity3a.button13
import kotlinx.android.synthetic.main.content_main_activity3a.button14
import kotlinx.android.synthetic.main.content_main_activity3a.button15
import kotlinx.android.synthetic.main.content_main_activity3a.button16
import kotlinx.android.synthetic.main.content_main_activity3a.button17
import kotlinx.android.synthetic.main.content_main_activity3a.button18
import kotlinx.android.synthetic.main.content_main_activity3a.button19
import kotlinx.android.synthetic.main.content_main_activity3a.button2
import kotlinx.android.synthetic.main.content_main_activity3a.button3
import kotlinx.android.synthetic.main.content_main_activity3a.button4
import kotlinx.android.synthetic.main.content_main_activity3a.button5
import kotlinx.android.synthetic.main.content_main_activity3a.button6
import kotlinx.android.synthetic.main.content_main_activity3a.button7
import kotlinx.android.synthetic.main.content_main_activity3a.button8
import kotlinx.android.synthetic.main.content_main_activity3a.button9

class ContentMainActivity3a : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var tryAgain2: Button
    private var remainingTime2 = 60
    private lateinit var textViewRemainingTime2: TextView
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var mediaPlayer: MediaPlayer? = null
    private var cardFlipSound: MediaPlayer? = null
    private var timerStarted = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main_activity3a)

        cardFlipSound = MediaPlayer.create(this, R.raw.card_flip)
        textViewRemainingTime2 = findViewById(R.id.textViewRemainingTime2)
        back = findViewById<Button>(R.id.back)
        back.setOnClickListener {
            val intent2 = Intent(this@ContentMainActivity3a, MainActivity::class.java)
            startActivity(intent2)
        }

        tryAgain2 = findViewById(R.id.tryAgain2)
        tryAgain2.setOnClickListener {
            val intentTryAgain2= Intent(this@ContentMainActivity3a, ContentMainActivity3a::class.java)
            startActivity(intentTryAgain2)
        }
        val images = mutableListOf(
            yu1, yu2, yu3, yu4, yu5, yu6, yu7, yu8, yu9, yu10,
            yu1, yu2, yu3, yu4, yu5,yu6, yu7, yu8, yu9, yu10,
        )

        val buttons = arrayOf(
            button ,button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12 , button13,
            button14,button15,button16,button17,button18,button19
        )

        val cartey = R.drawable.cartey
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
                    Toast.makeText(this@ContentMainActivity3a, "Game Over", Toast.LENGTH_SHORT).show()
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

        images.shuffle()
        for (i in 0..19) {
            buttons[i].setBackgroundResource(cartey)
            buttons[i].text = "cartey"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                //Timer starts at first click
                if (!timerStarted) {
                    handler.postDelayed(timerRunnable, 1000)
                    timerStarted = true
                }
                if(buttons[i].text=="cartey" && !turnOver)
                {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    clicked++
                    /*/ Verifică dacă sunetul anterior se redă încă
                    if (cardFlipSound?.isPlaying == true) {
                        cardFlipSound?.stop()
                        cardFlipSound?.prepare()
                    }

                    // Redare sunet
                    cardFlipSound?.start()*/

                    if(clicked==1)
                    {
                        lastClickedButton=buttons[i]
                        lastClickedImage=images[i].toString()
                    }
                    else if(clicked==2) {
                        if(images[i].toString()==lastClickedImage)
                        {
                            buttons[i].isClickable=false
                            lastClickedButton.isClickable=false
                            matchedPairs++
                            if(matchedPairs==images.size/2) {
                                Toast.makeText(this@ContentMainActivity3a, "Victory!", Toast.LENGTH_SHORT).show()
                                // All pairs have been matched
                                // Perform any desired actions, such as showing a message or restarting the game
                                mediaPlayer= MediaPlayer.create(this, R.raw.victory)
                                mediaPlayer?.setOnCompletionListener {
                                }
                                mediaPlayer?.let {
                                    try {
                                        it.prepare()
                                    } catch (e: IllegalStateException) {
                                        // Handle the exception or log an error message
                                    }
                                }
                                mediaPlayer?.start()
                                //timer stop
                                handler.removeCallbacks(timerRunnable)
                                val intent = Intent(this@ContentMainActivity3a,ContentMainActivity3b::class.java)
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
                            }, 600)
                        }
                        clicked =0
                    }
                }
            }
        }
    }
}