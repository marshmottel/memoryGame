
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
    private lateinit var button13:Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



         button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            val intent = Intent(this@MainActivity, ContentMainActivity::class.java)
            startActivity(intent)

        }
        button13 =  findViewById<Button>(R.id.button13)
        button13.setOnClickListener {

            val intent2 = Intent(this@MainActivity, ContentMainActivity2::class.java)
            startActivity(intent2)

        }

    }

}













