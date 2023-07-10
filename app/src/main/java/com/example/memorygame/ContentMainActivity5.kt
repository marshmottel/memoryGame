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
import kotlinx.android.synthetic.main.content_main.*
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main_activity5.button20
import kotlinx.android.synthetic.main.content_main_activity5.button21
import kotlinx.android.synthetic.main.content_main_activity5.button22
import kotlinx.android.synthetic.main.content_main_activity5.button23
import kotlinx.android.synthetic.main.content_main_activity5.button24
import kotlinx.android.synthetic.main.content_main_activity5.button25
import kotlinx.android.synthetic.main.content_main_activity5.button26
import kotlinx.android.synthetic.main.content_main_activity5.button27
import kotlinx.android.synthetic.main.content_main_activity5.button28
import kotlinx.android.synthetic.main.content_main_activity5.button29
import kotlinx.android.synthetic.main.content_main_activity5.button30
import kotlinx.android.synthetic.main.content_main_activity5.button31
import kotlinx.android.synthetic.main.content_main_activity5.button32
import kotlinx.android.synthetic.main.content_main_activity5.button33
import kotlinx.android.synthetic.main.content_main_activity5.button34
import kotlinx.android.synthetic.main.content_main_activity5.button35
import kotlinx.android.synthetic.main.content_main_activity5.button36
import kotlinx.android.synthetic.main.content_main_activity5.button37
import kotlinx.android.synthetic.main.content_main_activity5.button38
import kotlinx.android.synthetic.main.content_main_activity5.button39
import kotlinx.android.synthetic.main.content_main_activity5.button40
import kotlinx.android.synthetic.main.content_main_activity5.button41
import kotlinx.android.synthetic.main.content_main_activity5.button42
import kotlinx.android.synthetic.main.content_main_activity5.button43
import kotlinx.android.synthetic.main.content_main_activity5.button44
import kotlinx.android.synthetic.main.content_main_activity5.button45
import kotlinx.android.synthetic.main.content_main_activity5.button46
import kotlinx.android.synthetic.main.content_main_activity5.button47
import kotlinx.android.synthetic.main.content_main_activity5.button48
import kotlinx.android.synthetic.main.content_main_activity5.button49
import kotlinx.android.synthetic.main.content_main_activity5.tryAgain

class ContentMainActivity5 : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var tryAgainButton:Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var remainingTime = 60
    private lateinit var backHard: Button
    private lateinit var textViewRemainingTime: TextView
    private var timerStarted = false
    private var mediaPlayer: MediaPlayer? = null
    private var cardFlipSound: MediaPlayer? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main_activity5)

        cardFlipSound = MediaPlayer.create(this, R.raw.card_flip)
        backHard = findViewById(R.id.backHard)
        textViewRemainingTime = findViewById(R.id.textViewRemainingTime)


        back = findViewById<Button>(R.id.backHard)
        back.setOnClickListener {

            val intent2 = Intent(this@ContentMainActivity5, MainActivity::class.java)
            startActivity(intent2)

        }
        tryAgainButton = findViewById(R.id.tryAgain)
        tryAgainButton.setOnClickListener {
            val intentTryAgain=Intent(this@ContentMainActivity5, ContentMainActivity5::class.java)
            startActivity(intentTryAgain)
        }

        val images = mutableListOf(
            yu1,yu2,yu3,yu4,yu5,yu6,yu7,yu8,yu9, yu10, yu11,yu12,yu13,yu14,yu15,
            yu2,yu3,yu4,yu5,yu6,yu7,yu8,yu9, yu10, yu11,yu12,yu13,yu14,yu15
        )

        val buttons = arrayOf(
            button20,button21,button22,button23,button24,button25,
            button26,button27,button28,button29,button30,button31,button32,
            button33,button34,button35,button36,button37,button38,button39,
            button40,button41,button42,button43,button44,button45,button46,
            button47,button48,button49
        )
        val cartey = cartey
        var clicked = 0
        var turnOver = false
        //var lastClicked = -1
        //var allCardsTurned = false
        val handler = Handler()

        val timerRunnable = object : Runnable {
            override fun run() {
                remainingTime--
                // Actualizați timpul rămas în TextView
                textViewRemainingTime.text = remainingTime.toString()


                // Verificați dacă timpul a expirat
                if (remainingTime <= 0) {
                    // Afisați "Game Over" sau executați acțiunile corespunzătoare
                    Toast.makeText(this@ContentMainActivity5, "Game Over", Toast.LENGTH_SHORT).show()
                    for (button in buttons) {
                        button.isClickable = false
                    }
                    tryAgain.isClickable=true
                    tryAgain.visibility= View.VISIBLE


// Activează butonul "QUIT"
                    backHard.isClickable = true
                    // ...
                    return
                }

                // Programați următoarea actualizare a timerului peste 1 secundă
                handler.postDelayed(this, 1000)

            }
        }
        textViewRemainingTime.text = remainingTime.toString()

        images.shuffle()
        for (i in 0..29) {
            buttons[i].setBackgroundResource(cartey)
            buttons[i].text = "cartey"
            buttons[i].textSize = 0.0F

            buttons[i].setOnClickListener {
                //Timer starts at first click
                if (!timerStarted) {
                    handler.postDelayed(timerRunnable, 1000)
                    timerStarted = true
                }
                if(buttons[i].text=="cartey" && !turnOver) {
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
                            if(matchedPairs==images.size/2) {
                                Toast.makeText(this@ContentMainActivity5, "Victory!", Toast.LENGTH_SHORT).show()
                                mediaPlayer=MediaPlayer.create(this, R.raw.wow)
                                mediaPlayer?.setOnCompletionListener {

                                }
                                mediaPlayer?.start()
                                //timer stop
                                handler.removeCallbacks(timerRunnable)
                                val intent = Intent(this@ContentMainActivity5,ContentMainActivity6::class.java)
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
                            }, 400)
                        }
                        clicked =0
                    }
                }
            }
        }
    }
}

