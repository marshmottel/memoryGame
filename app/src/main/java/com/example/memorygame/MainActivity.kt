
package com.example.memorygame
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.memorygame.R.drawable.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



         button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            //codul pentru deschiderea jocului
            val intent = Intent(this@MainActivity, ContentMainActivity::class.java)
            startActivity(intent)

        }



/*
        setSupportActionBar(toolbar)

        val images: MutableList<Int> =
            mutableListOf(damacaro, nouacupa, patrutrefla, popatrefla, valetcaro, zecepica, damacaro, nouacupa, patrutrefla, popatrefla, valetcaro, zecepica)

        val buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12, )

        val cardBack = cardback
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var allCardsTurned = false

        images.shuffle()
        for(i in 0..11){
            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                } else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
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

            */

    }

}













