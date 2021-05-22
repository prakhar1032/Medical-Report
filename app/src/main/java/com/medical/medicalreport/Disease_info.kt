package com.medical.medicalreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*

class Disease_info : AppCompatActivity() {

    var EnterTitle: TextView? = null
    var DiseaseDescription: TextView? = null
    var DiseaseCause: TextView? = null
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_info)

        EnterTitle = findViewById(R.id.entertitle_info)
        DiseaseDescription = findViewById(R.id.diseasediscription_info)
        DiseaseCause = findViewById(R.id.diseasecause_info)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(firebaseAuth?.currentUser!!.uid)



        firebaseDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(task: DataSnapshot) {

                if (task.exists()) {
                    val Entertitle = task.child("EnterTitle").value as String
                    val Diseasedescription = task.child("DiseaseDescription").value as String
                    val Diseasecause = task.child("DiseaseCause").value as String

                    EnterTitle?.text = Entertitle
                    DiseaseDescription?.text = Diseasedescription
                    DiseaseCause?.text = Diseasecause

                }

            }


        })


    }
}