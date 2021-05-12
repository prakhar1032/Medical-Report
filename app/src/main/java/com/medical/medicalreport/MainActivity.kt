package com.medical.medicalreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<ImageButton>(R.id.diseases)
        button.setOnClickListener{
            startActivity(Intent(this@MainActivity,Diseases::class.java))
        }
    }


}