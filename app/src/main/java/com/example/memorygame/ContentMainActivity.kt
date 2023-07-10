package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.content_main.*
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main.button1
import kotlinx.android.synthetic.main.content_main.button10
import kotlinx.android.synthetic.main.content_main.button11
import kotlinx.android.synthetic.main.content_main.button12
import kotlinx.android.synthetic.main.content_main.button2
import kotlinx.android.synthetic.main.content_main.button3
import kotlinx.android.synthetic.main.content_main.button4
import kotlinx.android.synthetic.main.content_main.button5
import kotlinx.android.synthetic.main.content_main.button6
import kotlinx.android.synthetic.main.content_main.button7
import kotlinx.android.synthetic.main.content_main.button8
import kotlinx.android.synthetic.main.content_main.button9
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main_activity6.tryAgain


class ContentMainActivity : AppCompatActivity() {
    private lateinit var button15: Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var remainingTime = 60
    private lateinit var textViewRemainingTime: TextView
    private var timerStarted = false
    private var mediaPlayer: MediaPlayer? = null
    private var cardFlipSound: MediaPlayer? = null


    // Variables for the in-game stopwatch
   // private val totalTime = 60000 // timpul total în milisecunde (60 de secunde)
  //  private var timeLeft = totalTime // timpul rămas în milisecunde
  //  private lateinit var timer: CountDownTimer // timer-ul

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        cardFlipSound = MediaPlayer.create(this, R.raw.card_flip)
        textViewRemainingTime = findViewById(R.id.textViewRemainingTime3)


        //The implementation of the algorithm used for time leakage


        button15 = findViewById<Button>(R.id.back)
        button15.setOnClickListener {

            val intent2 = Intent(this@ContentMainActivity, MainActivity::class.java)
            startActivity(intent2)

        }


        val images = mutableListOf(
            damacaro, nouacupa, patrutrefla, popatrefla, valetcaro, zecepica,
            damacaro, nouacupa, patrutrefla, popatrefla, valetcaro, zecepica
        )

        val buttons = arrayOf(
            button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12
        )

        val cardBack = cardback
        var clicked = 0
        var turnOver = false
        //var lastClicked = -1
      //  var allCardsTurned = false
        val handler = Handler()

        val timerRunnable = object : Runnable {
            override fun run() {
                remainingTime--
                // Actualizați timpul rămas în TextView
                textViewRemainingTime.text = remainingTime.toString()


                // Verificați dacă timpul a expirat
                if (remainingTime <= 0) {
                    // Afisați "Game Over" sau executați acțiunile corespunzătoare
                    Toast.makeText(this@ContentMainActivity, "Game Over", Toast.LENGTH_SHORT).show()
                    for (button in buttons) {
                        button.isClickable = false
                    }
                    tryAgain.isClickable=true
                    tryAgain.visibility= View.VISIBLE


// Activează butonul "QUIT"
                    back.isClickable = true
                    // ...
                    return
                }

                // Programați următoarea actualizare a timerului peste 1 secundă
                handler.postDelayed(this, 1000)

            }
        }
        textViewRemainingTime.text = remainingTime.toString()

        images.shuffle()
        for (i in 0 until buttons.size) {
            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F

            buttons[i].setOnClickListener {
                //Timer starts at first click
                if (!timerStarted) {
                    handler.postDelayed(timerRunnable, 1000)
                    timerStarted = true
                }
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    clicked++

                    // Verifică dacă sunetul anterior se redă încă
                    if (cardFlipSound?.isPlaying == true) {
                        cardFlipSound?.stop()
                        cardFlipSound?.prepare()
                    }

                    // Redare sunet
                    cardFlipSound?.start()


                    if (clicked == 1) {
                        lastClickedButton = buttons[i]
                        lastClickedImage = images[i].toString()
                    } else if (clicked == 2) {
                        if (images[i].toString() == lastClickedImage) {
                            buttons[i].isClickable = false
                            lastClickedButton.isClickable = false
                            matchedPairs++
                            if (matchedPairs == images.size / 2) {
                                Toast.makeText(this@ContentMainActivity, "Victory!", Toast.LENGTH_SHORT).show()

                                // All pairs have been matched
                            // Perform any desired actions, such as showing a message or restarting the game
                                mediaPlayer= MediaPlayer.create(this, R.raw.wow)
                                mediaPlayer?.setOnCompletionListener {

                                }
                                mediaPlayer?.start()
                                val intent = intent
                                finish()
                                startActivity(intent)
                            }
                        }
                        else {
                            turnOver = true
                            Handler(Looper.getMainLooper()).postDelayed({
                                buttons[i].setBackgroundResource(cardBack)
                                buttons[i].text = "cardBack"
                                lastClickedButton.setBackgroundResource(cardBack)
                                lastClickedButton.text = "cardBack"
                                turnOver = false
                            }, 750)
                        }

                        clicked = 0
                    }
                }
            }
        }
    }

}