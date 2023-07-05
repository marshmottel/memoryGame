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
import kotlinx.android.synthetic.main.cantent_hard.backHard
import kotlinx.android.synthetic.main.cantent_hard.button20
import kotlinx.android.synthetic.main.cantent_hard.button21
import kotlinx.android.synthetic.main.cantent_hard.button22
import kotlinx.android.synthetic.main.cantent_hard.button23
import kotlinx.android.synthetic.main.cantent_hard.button24
import kotlinx.android.synthetic.main.cantent_hard.button25
import kotlinx.android.synthetic.main.cantent_hard.button26
import kotlinx.android.synthetic.main.cantent_hard.button27
import kotlinx.android.synthetic.main.cantent_hard.button28
import kotlinx.android.synthetic.main.cantent_hard.button29
import kotlinx.android.synthetic.main.cantent_hard.button30
import kotlinx.android.synthetic.main.cantent_hard.button31
import kotlinx.android.synthetic.main.cantent_hard.button32
import kotlinx.android.synthetic.main.cantent_hard.button33
import kotlinx.android.synthetic.main.cantent_hard.button34
import kotlinx.android.synthetic.main.cantent_hard.button35
import kotlinx.android.synthetic.main.cantent_hard.button36
import kotlinx.android.synthetic.main.cantent_hard.button37
import kotlinx.android.synthetic.main.cantent_hard.button38
import kotlinx.android.synthetic.main.cantent_hard.button39
import kotlinx.android.synthetic.main.cantent_hard.button40
import kotlinx.android.synthetic.main.cantent_hard.button41
import kotlinx.android.synthetic.main.cantent_hard.button42
import kotlinx.android.synthetic.main.cantent_hard.button43
import kotlinx.android.synthetic.main.cantent_hard.button44
import kotlinx.android.synthetic.main.cantent_hard.button45
import kotlinx.android.synthetic.main.cantent_hard.button46
import kotlinx.android.synthetic.main.cantent_hard.button47
import kotlinx.android.synthetic.main.cantent_hard.button48
import kotlinx.android.synthetic.main.cantent_hard.button49


class ContentMainActivity4 : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var mediaPlayer: MediaPlayer? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cantent_hard)

        back = findViewById<Button>(R.id.backHard)
        back.setOnClickListener {

            val intent2 = Intent(this@ContentMainActivity4, MainActivity::class.java)
            startActivity(intent2)

        }

        val images = mutableListOf(
            elephant, giraffe, gorilla, hippopotamus, kangaroo, koala, leopard, lion, monkey, panda,
            raccoon, sloth, snake, tiger, zebra,
            elephant, giraffe, gorilla, hippopotamus, kangaroo, koala, leopard, lion, monkey, panda,
            raccoon, sloth, snake, tiger, zebra,
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
        var lastClicked = -1
        var allCardsTurned = false

        images.shuffle()
        for (i in 0..29) {
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
                                        Toast.makeText(this@ContentMainActivity4, "Victory!", Toast.LENGTH_SHORT).show()
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

