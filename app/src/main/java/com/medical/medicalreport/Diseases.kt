package com.medical.medicalreport

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.inputmethod.InputConnectionCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class Diseases : AppCompatActivity() {

    var disease_name: EditText? = null
    var disease_2_name: EditText? = null
    var disease_3_name: EditText? = null
    var update_btn: Button? = null
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diseases)

        disease_name = findViewById(R.id.disease_name)
        disease_2_name = findViewById(R.id.disease_2name)
        disease_3_name = findViewById(R.id.disease_3name)
        update_btn = findViewById(R.id.disease_info)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(firebaseAuth?.currentUser!!.uid)

        update_btn?.setOnClickListener {

            SaveUserInfo()
        }

    }

    private fun SaveUserInfo() {


        val firstdisease = disease_name?.text.toString().trim()
        val seconddisease = disease_2_name?.text.toString().trim()
        val thirddisease = disease_3_name?.text.toString().trim()

        if (TextUtils.isEmpty(firstdisease)) {
            Toast.makeText(
                applicationContext,
                "Please Enter something in this Field",
                Toast.LENGTH_SHORT
            ).show()
        } else if (TextUtils.isEmpty(seconddisease)) {
            Toast.makeText(
                applicationContext,
                "Please Enter something in this Field",
                Toast.LENGTH_SHORT
            ).show()
        } else if (TextUtils.isEmpty(thirddisease)) {
            Toast.makeText(
                applicationContext,
                "Please Enter something in this Field",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val userinfo = HashMap<String, Any>()
            userinfo.put("firstDisease", firstdisease)
            userinfo.put("secondDisease", seconddisease)
            userinfo.put("thirdDisease", thirddisease)

            firebaseDatabase?.updateChildren(userinfo)
                ?.addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(task: Task<Void>) {

                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Your information is updated",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val error = task.exception?.message
                            Toast.makeText(applicationContext, "Error" + error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                })

        }

    }
}