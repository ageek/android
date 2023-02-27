package com.example.random

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomButton = findViewById<Button>(R.id.generateButton)
        randomButton.setOnClickListener {
            val randomNumText = findViewById<TextView>(R.id.randomNumberText)
            randomNumText.text = "New Random number: ${Random.nextInt(0, 1000)}"
        }
    }
}