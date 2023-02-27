package com.example.timepickerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import java.sql.Time

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picker = findViewById<TimePicker>(R.id.timepicker)
        val btn = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.text)

        btn.setOnClickListener {
            val hour = picker.hour
            val minute = picker.minute
            textView.text = "Selected time is: $hour:$minute"
        }

    }
}