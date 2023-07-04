
package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.widget.PopupMenu


class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var button13:Button
    private lateinit var button14:Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val menuButton = findViewById<Button>(R.id.button14)
        menuButton.setOnClickListener { v ->
            val popupMenu = PopupMenu(this@MainActivity, v)
            val inflater: MenuInflater = popupMenu.menuInflater
            inflater.inflate(R.menu.menu_main, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.button -> {
                        val page2 = Intent(this@MainActivity, ContentMainActivity::class.java)
                        startActivity(page2)
                        true
                    }
                    R.id.button13 -> {
                        val page2 = Intent(this@MainActivity, ContentMainActivity2::class.java)
                        startActivity(page2)
                        true
                    }
                    else -> false
                }
            }
        }


    }
/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.button -> {
                val intentPagina1 = Intent(this@MainActivity, ContentMainActivity::class.java)
                startActivity(intentPagina1)
                return true
            }

            R.id.button13 -> {
                val intentPagina2 = Intent(this@MainActivity, ContentMainActivity2::class.java)
                startActivity(intentPagina2)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


*/
}













