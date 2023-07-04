package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
                if (buttons[i].text == "cartey" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                } else if (buttons[i].text !in "cartey") {
                    buttons[i].setBackgroundResource(cartey)
                    buttons[i].text = "cartey"
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