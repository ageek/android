package com.example.beeradvisor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val findBeer = findViewById<Button>(R.id.find_beer)
        findBeer.setOnClickListener {

            val beerColor = findViewById<Spinner>(R.id.beer_color)
            val color = beerColor.selectedItem.toString()

            val brands = findViewById<TextView>(R.id.brands)
            brands.text = "Beer Color is: ${getBeers(color)} "
        }
    }

    fun getBeers(color: String): List<String> {
        return when (color.lowercase()) {
            "green" -> listOf("Gail, rail , thail", "lagar lake")
            "blue" -> listOf("Gblusing blue", "glusih blue")
            "red" -> listOf("reddish red", "ddish red")
            "yellow" -> listOf("yellowish yellow")
            "orange" -> listOf("orangish orange", "reddings orange")
            "purple" -> listOf("purplish purple")
            "pink" -> listOf("pikinsh pink", "reddings pink")
            else -> listOf("whitish white")

        }
    }
}