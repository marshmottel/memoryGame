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
import kotlinx.android.synthetic.main.cantent_hard.tryAgain

class ContentMainActivity6 : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var tryAgainButton: Button
    private lateinit var lastClickedButton: Button
    private lateinit var lastClickedImage: String
    private var matchedPairs = 0
    private var mediaPlayer: MediaPlayer? = null
    private var remainingTime = 60
    private lateinit var backHard: Button
    private lateinit var textViewRemainingTime: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main_activity6)
        backHard = findViewById(R.id.backHard)
        textViewRemainingTime = findViewById(R.id.textViewRemainingTime)


        back = findViewById<Button>(R.id.backHard)
        back.setOnClickListener {

            val intent2 = Intent(this@ContentMainActivity6, MainActivity::class.java)
            startActivity(intent2)

        }
        tryAgainButton = findViewById(R.id.tryAgain)
        tryAgainButton.setOnClickListener {
            val intentTryAgain= Intent(this@ContentMainActivity6, ContentMainActivity4::class.java)
            startActivity(intentTryAgain)
        }

        val images = mutableListOf(
            R.drawable.elephant,
            R.drawable.giraffe,
            R.drawable.gorilla,
            R.drawable.hippopotamus,
            R.drawable.kangaroo,
            R.drawable.koala,
            R.drawable.leopard,
            R.drawable.lion,
            R.drawable.monkey,
            R.drawable.panda,
            R.drawable.raccoon,
            R.drawable.sloth,
            R.drawable.snake,
            R.drawable.tiger,
            R.drawable.zebra,
            R.drawable.elephant,
            R.drawable.giraffe,
            R.drawable.gorilla,
            R.drawable.hippopotamus,
            R.drawable.kangaroo,
            R.drawable.koala,
            R.drawable.leopard,
            R.drawable.lion,
            R.drawable.monkey,
            R.drawable.panda,
            R.drawable.raccoon,
            R.drawable.sloth,
            R.drawable.snake,
            R.drawable.tiger,
            R.drawable.zebra,
        )

        val buttons = arrayOf(
            button20,button21,button22,button23,button24,button25,
            button26,button27,button28,button29,button30,button31,button32,
            button33,button34,button35,button36,button37,button38,button39,
            button40,button41,button42,button43,button44,button45,button46,
            button47,button48,button49
        )
        val backcards = R.drawable.backcards
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
                    Toast.makeText(this@ContentMainActivity6, "Game Over", Toast.LENGTH_SHORT).show()
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
// Porniți timerul
        handler.postDelayed(timerRunnable, 1000)

        images.shuffle()
        for (i in 0..29) {
            buttons[i].setBackgroundResource(backcards)
            buttons[i].text = "backcards"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if(buttons[i].text=="backcards" && !turnOver)
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
                                mediaPlayer= MediaPlayer.create(this, R.raw.wow)
                                mediaPlayer?.setOnCompletionListener {
                                    Handler(Looper.getMainLooper()).postDelayed({
                                        Toast.makeText(this@ContentMainActivity6, "Victory!", Toast.LENGTH_SHORT).show()
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
                                buttons[i].setBackgroundResource(backcards)
                                buttons[i].text = "backcards"
                                lastClickedButton.setBackgroundResource(backcards)
                                lastClickedButton.text = "backcards"
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

