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


class ContentMainActivity2 : AppCompatActivity() {
    private lateinit var button15: Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var mediaPlayer: MediaPlayer? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        button15 = findViewById<Button>(R.id.back)
        button15.setOnClickListener {


            val intent2 = Intent(this@ContentMainActivity2, MainActivity::class.java)
            startActivity(intent2)

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

        images.shuffle()
        for (i in 0 until buttons.size) {
            buttons[i].setBackgroundResource(backyugioh)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F

            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    clicked++

                    if (clicked == 1) {
                        lastClickedButton = buttons[i]
                        lastClickedImage = images[i].toString()
                    } else if (clicked == 2) {
                        if (images[i].toString() == lastClickedImage) {
                            buttons[i].isClickable = false
                            lastClickedButton.isClickable = false
                            matchedPairs++
                            if (matchedPairs == images.size / 2) {
                                // All pairs have been matched
                                // Perform any desired actions, such as showing a message or restarting the game
                                mediaPlayer = MediaPlayer.create(this, R.raw.wow)
                                mediaPlayer?.setOnCompletionListener {
                                    Handler(Looper.getMainLooper()).postDelayed({
                                        Toast.makeText(this@ContentMainActivity2, "Victory!", Toast.LENGTH_SHORT).show()
                                    }, 100)
                                    // Acțiuni de efectuat după încheierea redării sunetului
                                    // De exemplu, poți afișa un mesaj de victorie sau reseta jocul
                                }
                                mediaPlayer?.start()
                                val intent = intent
                                finish()
                                startActivity(intent)
                            }
                        } else {
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