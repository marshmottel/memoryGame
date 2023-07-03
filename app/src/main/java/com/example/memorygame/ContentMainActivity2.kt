package com.example.memorygame

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)


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
        var lastClicked = -1
        var allCardsTurned = false

        images.shuffle()
        for (i in 0..11) {
            buttons[i].setBackgroundResource(backyugioh)
            buttons[i].text = "backyugioh"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "backyugioh" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                } else if (buttons[i].text !in "backyugioh") {
                    buttons[i].setBackgroundResource(backyugioh)
                    buttons[i].text = "backyugioh"
                    clicked--
                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        clicked = 0
                    }
                } else if (clicked == 0) {
                    turnOver = false
                }
            }
        }
    }
}