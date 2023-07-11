package com.example.memorygame

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.memorygame.R
import kotlinx.android.synthetic.main.content_main.back


class Completed : AppCompatActivity() {
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.completed)

        back = findViewById<Button>(R.id.back)
        back.setOnClickListener {

            val intent2 = Intent(this@Completed, MainActivity::class.java)
            startActivity(intent2)

        }

    }
}