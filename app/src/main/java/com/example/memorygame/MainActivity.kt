package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var cardsButton: Button
    private lateinit var buttonyu: Button
    private lateinit var menuButton: Button
    //private lateinit var menu2Button:Button
    private lateinit var mediuButton:Button
    private lateinit var hardButton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardsButton = findViewById<Button>(R.id.cardsButton)
        cardsButton.setOnClickListener {

            val intent = Intent(this@MainActivity, ContentMainActivity::class.java)
            startActivity(intent)

        }
        mediuButton =findViewById<Button>(R.id.medium)
        mediuButton.setOnClickListener {
        val intentmediu=Intent(this@MainActivity,ContentMainActivity3::class.java)
        startActivity(intentmediu)
        }


        buttonyu = findViewById<Button>(R.id.buttonyu)
        buttonyu.setOnClickListener {

            val intent2 = Intent(this@MainActivity, ContentMainActivity2::class.java)
            startActivity(intent2)

        }
        hardButton=findViewById(R.id.hard)
        hardButton.setOnClickListener {
            val intentHard=Intent(this@MainActivity,ContentMainActivity4::class.java)
            startActivity(intentHard)
        }


/*
        menuButton = findViewById<Button>(R.id.menuButton)
        menuButton.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, menuButton)
            popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.button00 -> {
                        val intent00 =
                            Intent(this@MainActivity, ContentMainActivity::class.java)
                        startActivity(intent00)
                        true
                    }

                    R.id.button11 -> {
                        val intent11 =
                            Intent(this@MainActivity, ContentMainActivity2::class.java)
                        startActivity(intent11)
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }

        */

        menuButton=findViewById(R.id.levelButton)
        menuButton.setOnClickListener {
            val popupMenu=PopupMenu( this@MainActivity,menuButton)
            popupMenu.menuInflater.inflate(R.menu.menu_level,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item->
                when(item.itemId){
                    R.id.easy->{
                        val intentLow=Intent(this@MainActivity,ContentMainActivity::class.java)
                        startActivity(intentLow)
                        true
                    }
                    R.id.mediu->{
                        val intentMediu=Intent(this@MainActivity,ContentMainActivity3::class.java)
                        startActivity(intentMediu)
                        true
                    }
                    R.id.hard->{
                        val intentHard=Intent(this@MainActivity,ContentMainActivity4::class.java)
                        startActivity(intentHard)
                        true
                    }
                    else ->false

                }
            }
            popupMenu.show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
     //   menuInflater.inflate(R.menu.menu_level,menu)
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
                            val intentLow =
                                Intent(this@MainActivity, ContentMainActivity::class.java)
                            startActivity(intentLow)
                            true
                        }

                        R.id.mediu -> {
                            val intentMediu =
                                Intent(this@MainActivity, ContentMainActivity2::class.java)
                            startActivity(intentMediu)
                            true
                        }
                        R.id.hard->{
                            val intentHard =Intent(this@MainActivity,ContentMainActivity4::class.java)
                            startActivity(intentHard)
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
}













