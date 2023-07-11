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
import kotlinx.android.synthetic.main.content_main_activity1.*
class ContentMainActivity1 : AppCompatActivity() {
    private lateinit var button15: Button
    private lateinit var tryAgain3: Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var remainingTime3 = 60
    private var mediaPlayer: MediaPlayer? = null
    private var cardFlipSound: MediaPlayer? = null
    private lateinit var textViewRemainingTime3: TextView
    private var timerStarted = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        cardFlipSound = MediaPlayer.create(this, R.raw.card_flip)
        textViewRemainingTime3 = findViewById(R.id.textViewRemainingTime3)
        button15 = findViewById<Button>(R.id.back)
        button15.setOnClickListener {

            val intent2 = Intent(this@ContentMainActivity1, MainActivity::class.java)
            startActivity(intent2)

        }
        tryAgain3 = findViewById(R.id.tryAgain3)
        tryAgain3.setOnClickListener {
            val intentTryAgain2= Intent(this@ContentMainActivity1, ContentMainActivity1::class.java)
            startActivity(intentTryAgain2)
        }
        val images = mutableListOf(
            yu1, yu2, yu3, yu4, yu5, yu6,
            yu1, yu2, yu3, yu4, yu5, yu6,
        )
        val buttons = arrayOf(
            button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12
        )

        val backyugioh = backyugioh
        var clicked = 0
        var turnOver = false
        //var lastClicked = -1
        //var allCardsTurned = false
        val handler = Handler()
        val timerRunnable = object : Runnable {
            override fun run() {
                remainingTime3--
                // Actualizați timpul rămas în TextView
                textViewRemainingTime3.text = remainingTime3.toString()


                // Verificați dacă timpul a expirat
                if (remainingTime3 <= 0) {
                    // Afisați "Game Over" sau executați acțiunile corespunzătoare
                    Toast.makeText(this@ContentMainActivity1, "Game Over", Toast.LENGTH_SHORT).show()
                    for (button in buttons) {
                        button.isClickable = false
                    }
                    tryAgain3.isClickable=true
                    tryAgain3.visibility= View.VISIBLE


// Activează butonul "QUIT"
                    back.isClickable = true
                    // ...
                    return
                }

                // Programați următoarea actualizare a timerului peste 1 secundă
                handler.postDelayed(this, 1000)

            }
        }
        textViewRemainingTime3.text = remainingTime3.toString()

        images.shuffle()
        for (i in 0 until buttons.size) {
            buttons[i].setBackgroundResource(backyugioh)
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

                    /*/ Verifică dacă sunetul anterior se redă încă
                    if (cardFlipSound?.isPlaying == true) {
                        cardFlipSound?.stop()
                        cardFlipSound?.prepare()
                    }

                    // Redare sunet
                    cardFlipSound?.start()*/

                    if (clicked == 1) {
                        lastClickedButton = buttons[i]
                        lastClickedImage = images[i].toString()
                    }
                    else if (clicked == 2) {
                        if (images[i].toString() == lastClickedImage) {
                            buttons[i].isClickable = false
                            lastClickedButton.isClickable = false
                            matchedPairs++
                            if (matchedPairs == images.size / 2) {
                                Toast.makeText(this@ContentMainActivity1, "Victory!", Toast.LENGTH_SHORT).show()
                                // All pairs have been matched
                                // Perform any desired actions, such as showing a message or restarting the game
                                mediaPlayer = MediaPlayer.create(this, R.raw.victory)
                                mediaPlayer?.setOnCompletionListener {
                                    // Acțiuni de efectuat după încheierea redării sunetului
                                    // De exemplu, poți afișa un mesaj de victorie sau reseta jocul
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
                                val intent2 = Intent(this@ContentMainActivity1,ContentMainActivity2::class.java)
                                finish()
                                startActivity(intent2)
                            }
                        }
                        else {
                            turnOver = true
                            Handler(Looper.getMainLooper()).postDelayed({
                                buttons[i].setBackgroundResource(backyugioh)
                                buttons[i].text = "cardBack"
                                lastClickedButton.setBackgroundResource(backyugioh)
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