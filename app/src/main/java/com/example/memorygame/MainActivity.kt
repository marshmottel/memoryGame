package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.PopupMenu
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var menuButton: Button
    private var backgroundMusic: MediaPlayer? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        /*/Background music start
        backgroundMusic = MediaPlayer.create(this, R.raw.background_trap)
        backgroundMusic?.isLooping = true // Set the music to loop indefinitely
        backgroundMusic?.start() // Start playing the music*/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuButton = findViewById(R.id.levelButton)
        menuButton.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, menuButton)
            popupMenu.menuInflater.inflate(R.menu.menu_level, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.option11 -> {
                        val intentLow = Intent(this@MainActivity, ContentMainActivity::class.java)
                        startActivity(intentLow)
                        true
                    }

                    R.id.option12 -> {
                        val intentMediu =
                            Intent(this@MainActivity, ContentMainActivity1::class.java)
                        startActivity(intentMediu)
                        true
                    }

                    R.id.option13 -> {
                        val intentHard = Intent(this@MainActivity, ContentMainActivity2::class.java)
                        startActivity(intentHard)
                        true
                    }

                    R.id.option21 -> {
                        val intentLow = Intent(this@MainActivity, ContentMainActivity3::class.java)
                        startActivity(intentLow)
                        true
                    }

                    R.id.option22 -> {
                        val intentMediu =
                            Intent(this@MainActivity, ContentMainActivity3a::class.java)
                        startActivity(intentMediu)
                        true
                    }

                    R.id.option23 -> {
                        val intentHard = Intent(this@MainActivity, ContentMainActivity3b::class.java)
                        startActivity(intentHard)
                        true
                    }

                    R.id.option31 -> {
                        val intentLow = Intent(this@MainActivity, ContentMainActivity4::class.java)
                        startActivity(intentLow)
                        true
                    }

                    R.id.option32 -> {
                        val intentMediu =
                            Intent(this@MainActivity, ContentMainActivity5::class.java)
                        startActivity(intentMediu)
                        true
                    }

                    R.id.option33 -> {
                        val intentHard = Intent(this@MainActivity, ContentMainActivity6::class.java)
                        startActivity(intentHard)
                        true
                    }

                    else -> false

                }
            }
            popupMenu.show()
        }

    }
}


/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.levelButton -> {
                val popupMenu = PopupMenu(this@MainActivity, levelButton)
                popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.easy -> {
                          //  val intentLow =
                              //  Intent(this@MainActivity, ContentMainActivity::class.java)
                          //  startActivity(intentLow)
                            true
                        }

                        R.id.mediu -> {
                          //  val intentMediu =
                             //   Intent(this@MainActivity, ContentMainActivity2::class.java)
                           // startActivity(intentMediu)
                            true
                        }
                        R.id.hard->{
                          //  val intentHard =Intent(this@MainActivity,ContentMainActivity4::class.java)
                           // startActivity(intentHard)
                            true
                        }

                        else -> false
                    }
                }
                popupMenu.show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }


    }
    */














