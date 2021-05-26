package com.medical.medicalreport

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.DataSnapshot

class MainActivity : AppCompatActivity() {
    companion object {
        const val NAME_EXTRA = "name_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val display = findViewById<TextView>(R.id.textView)
        val name = intent.getStringExtra(NAME_EXTRA)
        display.text = " Welcome $name"



    }
    fun medicalreport(view: View) {
        val intent = Intent(this,MedicalReport::class.java)
        startActivity(intent)
    }

    fun diseases(view: View) {
        val intent = Intent(this,Diseases::class.java)
        startActivity(intent)
    }





}




