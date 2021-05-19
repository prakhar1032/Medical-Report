package com.medical.medicalreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*

class Disease_info : AppCompatActivity() {

    var firstDisease: TextView? = null
    var secondDisease: TextView? = null
    var thirdDisease: TextView? = null
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_info)

        firstDisease = findViewById(R.id.first_Disease)
        secondDisease = findViewById(R.id.second_Disease)
        thirdDisease = findViewById(R.id.third_Disease)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(firebaseAuth?.currentUser!!.uid)



        firebaseDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(task: DataSnapshot) {

                if (task.exists()) {
                    val firstdisease = task.child("firstDisease").value as String
                    val seconddisease = task.child("secondDisease").value as String
                    val thirdisease = task.child("thirdDisease").value as String

                    firstDisease?.text = firstdisease
                    firstDisease?.text = seconddisease
                    thirdDisease?.text = thirdisease

                }

            }


        })


    }
}